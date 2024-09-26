package com.example.contasapagar.application.controller;

import com.example.contasapagar.domain.model.Conta;
import com.example.contasapagar.domain.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/import")
public class FileUploadController {

    @Autowired
    private ContaService contaService;

    @PostMapping("/csv")
    public void importCSV(@RequestParam("file") MultipartFile file) throws Exception {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                Conta conta = new Conta();
                conta.setDataVencimento(LocalDate.parse(dados[0]));
                conta.setDataPagamento(LocalDate.parse(dados[1]));
                conta.setValor(new BigDecimal(dados[2]));
                conta.setDescricao(dados[3]);
                conta.setSituacao(dados[4]);
                contaService.save(conta);
            }
        }
    }
}
