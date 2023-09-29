package com.api.apifinanceiro.validations;

import com.api.apifinanceiro.base.dtos.BaseErrorDto;
import com.api.apifinanceiro.constants.MensagensErros;
import com.api.apifinanceiro.dto.FinanceiroCriarDto;
import com.api.apifinanceiro.models.enums.CargosEnum;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class CriarFinanceiroValidate {

  public List<BaseErrorDto> validar(FinanceiroCriarDto financeiroCriarDto) {
    List<BaseErrorDto> errors = validarCamposRequeridos(financeiroCriarDto);
    return errors.size() > 0 ? errors : validarCamposInvalidos(financeiroCriarDto, errors);
  }

  private List<BaseErrorDto> validarCamposRequeridos(FinanceiroCriarDto financeiroCriarDto) {
    List<BaseErrorDto> errors = new ArrayList<>();

    // Campos obrigatórios
    if (financeiroCriarDto.getIdFuncionario() == null) {
      errors.add(new BaseErrorDto("idFuncionario", MensagensErros.EMPTY_FIELD));
    }

    if (financeiroCriarDto.getCargo() == null) {
      errors.add(new BaseErrorDto("cargo", MensagensErros.EMPTY_FIELD));
    }

    if (financeiroCriarDto.getDataAdmissao() == null) {
      errors.add(new BaseErrorDto("dataAdmissao", MensagensErros.EMPTY_FIELD));
    }

    if (financeiroCriarDto.getSalario() == null) {
      errors.add(new BaseErrorDto("salario", MensagensErros.EMPTY_FIELD));
    }

    return errors;
  }

  private List<BaseErrorDto> validarCamposInvalidos(FinanceiroCriarDto financeiroCriarDto,
      List<BaseErrorDto> errors) {

    // Data de admissão não pode ser maior que hoje
    if (financeiroCriarDto.getDataAdmissao().isAfter(ZonedDateTime.now())) {
      errors.add(new BaseErrorDto("dataAdmissao", MensagensErros.INVALID_FIELD));
    }

    // Cargo deve ser um dos existentes
    if (CargosEnum.cargoSelecionado(financeiroCriarDto.getCargo().toString()) == null) {
      errors.add(new BaseErrorDto("cargo", MensagensErros.INVALID_FIELD));
    }

    return errors;
  }
}
