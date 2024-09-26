package com.example.contasapagar.domain.service;

import com.example.contasapagar.domain.model.Conta;
import com.example.contasapagar.domain.repository.ContaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ContaServiceTest {

    @Test
    void saveConta() {
        
        ContaRepository contaRepository = mock(ContaRepository.class);

        ContaService contaService = new ContaService(contaRepository);

        Conta conta = new Conta();
        conta.setDataVencimento(LocalDate.now());
        conta.setValor(BigDecimal.TEN);

        when(contaRepository.save(any(Conta.class))).thenReturn(conta);

        Conta savedConta = contaService.save(conta);

        assertNotNull(savedConta);

        verify(contaRepository, times(1)).save(conta);
    }
}
