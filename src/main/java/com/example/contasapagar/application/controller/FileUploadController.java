package com.example.contasapagar.application.controller;

import com.example.contasapagar.domain.model.Conta;
import com.example.contasapagar.domain.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/import")
public class FileUploadController {

    @Autowired
    private ContaService contaService;

    @PostMapping("/csv")
    public List<EntityModel<Conta>> importCSV(@RequestParam("file") MultipartFile file) throws Exception {
        List<EntityModel<Conta>> contasResources = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String linha;
            boolean isFirstLine = true;

            while ((linha = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                
                String[] dados = linha.split(",");
                Conta conta = new Conta();
                conta.setDataVencimento(LocalDate.parse(dados[0]));
                conta.setDataPagamento(LocalDate.parse(dados[1]));
                conta.setValor(new BigDecimal(dados[2]));
                conta.setDescricao(dados[3]);
                conta.setSituacao(dados[4]);

                Conta savedConta = contaService.save(conta);
                EntityModel<Conta> resource = EntityModel.of(savedConta);
                resource.add(linkTo(methodOn(ContaController.class).getById(savedConta.getId())).withSelfRel());
                contasResources.add(resource);
            }
        }

        return contasResources;
    }
}
