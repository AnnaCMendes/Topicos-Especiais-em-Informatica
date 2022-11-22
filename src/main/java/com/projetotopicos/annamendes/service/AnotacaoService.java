package com.projetotopicos.annamendes.service;

import java.util.List;

import com.projetotopicos.annamendes.entity.Anotacao;

public interface AnotacaoService {

    public List<Anotacao> todas();

    public List<Anotacao> buscarNoTexto(String texto);

    public Anotacao salvar(Anotacao anotacao, String nomeUsuario);
    
}
