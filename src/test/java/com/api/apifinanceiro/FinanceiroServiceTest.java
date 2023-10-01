package com.api.apifinanceiro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.api.apifinanceiro.base.dtos.BaseDto;
import com.api.apifinanceiro.entities.dtos.FinanceiroCriarDto;
import com.api.apifinanceiro.entities.dtos.FinanceiroDto;
import com.api.apifinanceiro.entities.enums.CargosEnum;
import com.api.apifinanceiro.entities.models.FinanceiroModel;
import com.api.apifinanceiro.repositories.FinanceiroRepository;
import com.api.apifinanceiro.services.v1.FinanceiroService;
import java.time.ZonedDateTime;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

@DisplayName("FinanceiroService - Testes")
public class FinanceiroServiceTest {

  @MockBean
  private FinanceiroRepository financeiroRepository;

  @Autowired
  private FinanceiroService financeiroService;
  private FinanceiroCriarDto dtoFinanceiro;
  private FinanceiroModel financeiro;

  @BeforeEach
  public void setUp() {
    financeiroRepository = mock(FinanceiroRepository.class);
    financeiroService = new FinanceiroService(financeiroRepository);

    dtoFinanceiro = new FinanceiroCriarDto();
    dtoFinanceiro.setIdFuncionario(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
    dtoFinanceiro.setCargo(CargosEnum.valueOf("INSTRUTOR"));
    dtoFinanceiro.setDataAdmissao(ZonedDateTime.parse("2021-01-01T00:00:00.000Z"));
    dtoFinanceiro.setSalario(1000.00);
    dtoFinanceiro.setClt("123456789");
    dtoFinanceiro.setMatricula("123456789");
    dtoFinanceiro.setStatus(1);

    FinanceiroModel financeiro = new FinanceiroModel();
  }

  @Test
  @DisplayName("01 - Service - Criar financeiro válido")
  public void testarCriarFinanceiroValido() {
    UUID validUUID = UUID.randomUUID();

    FinanceiroModel financeiro = new FinanceiroModel();
    financeiro.setId(validUUID);

    when(financeiroRepository.save(any(FinanceiroModel.class))).thenReturn(financeiro);

    BaseDto<FinanceiroDto> responseEntity = (BaseDto<FinanceiroDto>)
        financeiroService.criarFinanceiro(dtoFinanceiro).getBody();

    assertEquals(HttpStatus.CREATED.value(), responseEntity.getResultado().getStatus());
    assertEquals("Financeiro criado com sucesso!", responseEntity.getResultado().getDescricao());
  }

  @Test
  @DisplayName("02 - Service - Criar financeiro inválido")
  public void testarCriarFinanceiroInvalido() {
  }
}
