package com.example.contasapagar.domain.service;

import com.example.contasapagar.domain.model.Conta;
import com.example.contasapagar.domain.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    
    @Autowired
    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta save(Conta conta) {
        return contaRepository.save(conta);
    }

    public Conta update(Long id, Conta conta) {
        Conta existingConta = contaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Conta not found"));
        existingConta.setDataVencimento(conta.getDataVencimento());
        existingConta.setDataPagamento(conta.getDataPagamento());
        existingConta.setValor(conta.getValor());
        existingConta.setDescricao(conta.getDescricao());
        existingConta.setSituacao(conta.getSituacao());
        return contaRepository.save(existingConta);
    }

    public void updateSituacao(Long id, String situacao) {
        Conta conta = contaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Conta not found"));
        conta.setSituacao(situacao);
        contaRepository.save(conta);
    }

    public Page<Conta> findAll(String descricao, LocalDate dataVencimento, Pageable pageable) {
        return contaRepository.findAll(pageable);
    }

    public Conta findById(Long id) {
        return contaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Conta not found"));
    }

    public BigDecimal getTotalPagoPorPeriodo(LocalDate inicio, LocalDate fim) {
        return contaRepository.sumByDateRange(inicio, fim);
    }
}
