package com.api.apifinanceiro.services.v1;

import com.api.apifinanceiro.base.dtos.BaseErrorDto;
import com.api.apifinanceiro.builders.ResponseErrorBuilder;
import com.api.apifinanceiro.builders.ResponseSuccessBuilder;
import com.api.apifinanceiro.dto.FinanceiroCriarDto;
import com.api.apifinanceiro.dto.FinanceiroDto;
import com.api.apifinanceiro.models.FinanceiroModel;
import com.api.apifinanceiro.repositories.FinanceiroRepository;
import com.api.apifinanceiro.validations.CriarFinanceiroValidate;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FinanceiroService {

  private FinanceiroRepository financeiroRepository;

  @Autowired
  public FinanceiroService(FinanceiroRepository financeiroRepository) {
    this.financeiroRepository = financeiroRepository;
  }

  @Transactional
  public ResponseEntity criarFinanceiro(FinanceiroCriarDto novoFinanceiroDto) {
    List<BaseErrorDto> erros = new CriarFinanceiroValidate().validar(novoFinanceiroDto);

    if (erros.size() > 0) {
      return new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, erros).get();
    }
    FinanceiroModel novoFinanceiro = new FinanceiroModel();

    novoFinanceiro.setIdFuncionario(novoFinanceiroDto.getIdFuncionario());
    novoFinanceiro.setCargo(novoFinanceiroDto.getCargo());
    novoFinanceiro.setDataAdmissao(novoFinanceiroDto.getDataAdmissao());
    novoFinanceiro.setSalario(novoFinanceiroDto.getSalario());
    novoFinanceiro.setClt(novoFinanceiroDto.getClt());
    novoFinanceiro.setMatricula(novoFinanceiroDto.getMatricula());
    novoFinanceiro.setStatus(1);

    UUID idFinanceiro = financeiroRepository.save(novoFinanceiro).getId();

    return new ResponseSuccessBuilder<FinanceiroDto>(
        HttpStatus.CREATED,
        new FinanceiroDto(idFinanceiro.toString()),
        "Financeiro criado com sucesso!"
    ).get();
  }
}