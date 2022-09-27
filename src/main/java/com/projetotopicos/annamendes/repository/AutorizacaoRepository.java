package com.projetotopicos.annamendes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetotopicos.annamendes.entity.Autorizacao;

public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long> {
    
    public Autorizacao findByNome(String nome);

}
