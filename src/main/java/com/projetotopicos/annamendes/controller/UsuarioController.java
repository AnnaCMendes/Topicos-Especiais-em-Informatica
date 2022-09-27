package com.projetotopicos.annamendes.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projetotopicos.annamendes.entity.Usuario;
import com.projetotopicos.annamendes.service.SegurancaService;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private SegurancaService segurancaService;

    @GetMapping
    public List<Usuario> buscarTodos() {
        return segurancaService.todosUsuarios();
    }

    @GetMapping(value = "/{id}")
    public Usuario buscarPorId(@PathVariable("id") Long id) {
         return segurancaService.buscarPorId(id);
    }

    @PostMapping
    public Usuario novoUsuario(@RequestBody Usuario usuario) {
        return segurancaService.novoUsuario(usuario);
    }

}