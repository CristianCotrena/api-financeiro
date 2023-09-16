package com.api.apifinanceiro.repositories;

import com.api.apifinanceiro.models.FinanceiroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FinanceiroRepository extends JpaRepository<FinanceiroModel, UUID> {

}
