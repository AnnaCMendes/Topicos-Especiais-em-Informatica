package com.projetotopicos.annamendes.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetotopicos.annamendes.entity.Anotacao;
import com.projetotopicos.annamendes.service.AnotacaoService;



@RestController
@RequestMapping(value = "/anotacao")
@CrossOrigin

public class AnotacaoController {
    @Autowired
    private AnotacaoService service;

    @GetMapping
    public List<Anotacao> buscarTodas() {
        return service.todas();
    }

    @GetMapping(value = "/{texto}")
    public List<Anotacao> buscarNoTexto(@PathVariable("texto") String texto) {
        return service.buscarNoTexto(texto);

    }

    @PostMapping
    public Anotacao salvar(@RequestBody Anotacao anotacao, Principal principal) {
        return service.salvar(anotacao, principal.getName());
    }
    
}
