package com.example.contasapagar.application.controller;

import com.example.contasapagar.domain.model.Conta;
import com.example.contasapagar.domain.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/contas")
public class ContaController {

    @Autowired  
    private ContaService contaService;

    @PostMapping
    public EntityModel<Conta> create(@RequestBody Conta conta) {
        Conta savedConta = contaService.save(conta);
        EntityModel<Conta> resource = EntityModel.of(savedConta);
        resource.add(linkTo(methodOn(ContaController.class).getById(savedConta.getId())).withSelfRel());
        return resource;
    }

    @PutMapping("/{id}")
    public EntityModel<Conta> update(@PathVariable Long id, @RequestBody Conta conta) {
        Conta updatedConta = contaService.update(id, conta);
        EntityModel<Conta> resource = EntityModel.of(updatedConta);
        resource.add(linkTo(methodOn(ContaController.class).getById(updatedConta.getId())).withSelfRel());
        return resource;
    }

    @PatchMapping("/{id}/situacao")
    public void updateSituacao(@PathVariable Long id, @RequestParam String situacao) {
        contaService.updateSituacao(id, situacao);
    }

    @GetMapping
    public Page<Conta> getAll(@RequestParam(required = false) String descricao,
                              @RequestParam(required = false) LocalDate dataVencimento,
                              Pageable pageable) {
        return contaService.findAll(descricao, dataVencimento, pageable);
    }

    @GetMapping("/{id}")
    public EntityModel<Conta> getById(@PathVariable Long id) {
        Conta conta = contaService.findById(id);
        EntityModel<Conta> resource = EntityModel.of(conta);
        resource.add(linkTo(methodOn(ContaController.class).getById(id)).withSelfRel());
        return resource;
    }

    @GetMapping("/total-pago")
    public BigDecimal getTotalPago(@RequestParam LocalDate inicio, @RequestParam LocalDate fim) {
        return contaService.getTotalPagoPorPeriodo(inicio, fim);
    }
}
