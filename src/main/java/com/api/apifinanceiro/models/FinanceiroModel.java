package com.api.apifinanceiro.models;

import com.api.apifinanceiro.models.enums.CargosEnum;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "FINANCEIRO")
public class FinanceiroModel implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false)
  private UUID idFuncionario;

  @Column(nullable = false)
  private CargosEnum cargo;

  @Column(nullable = false)
  private String dataAdmissao;

  @Column(nullable = false)
  private double salario;

  @Column(nullable = false)
  private String clt;

  @Column(nullable = false)
  private String matricula;

  @Column(nullable = false, columnDefinition = "int default 1")
  private int status;

  public FinanceiroModel(UUID id, UUID idFuncionario, CargosEnum cargo, String dataAdmissao,
      double salario, String clt, String matricula, int status) {
    this.id = id;
    this.idFuncionario = idFuncionario;
    this.cargo = cargo;
    this.dataAdmissao = dataAdmissao;
    this.salario = salario;
    this.clt = clt;
    this.matricula = matricula;
    this.status = status;
  }

  public FinanceiroModel() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getIdFuncionario() {
    return idFuncionario;
  }

  public void setIdFuncionario(UUID idFuncionario) {
    this.idFuncionario = idFuncionario;
  }

  public CargosEnum getCargo() {
    return cargo;
  }

  public void setCargo(CargosEnum cargo) {
    this.cargo = cargo;
  }

  public String getDataAdmissao() {
    return dataAdmissao;
  }

  public void setDataAdmissao(String dataAdmissao) {
    this.dataAdmissao = dataAdmissao;
  }

  public double getSalario() {
    return salario;
  }

  public void setSalario(double salario) {
    this.salario = salario;
  }

  public String getClt() {
    return clt;
  }

  public void setClt(String clt) {
    this.clt = clt;
  }

  public String getMatricula() {
    return matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
