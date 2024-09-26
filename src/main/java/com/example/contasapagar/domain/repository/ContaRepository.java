package com.example.contasapagar.domain.repository;

import com.example.contasapagar.domain.model.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.math.BigDecimal;
import java.time.LocalDate;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Query("SELECT c FROM Conta c WHERE c.dataVencimento BETWEEN :startDate AND :endDate")
    Page<Conta> findByDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable);

    @Query("SELECT SUM(c.valor) FROM Conta c WHERE c.dataVencimento BETWEEN :startDate AND :endDate")
    BigDecimal sumByDateRange(LocalDate startDate, LocalDate endDate);
}
