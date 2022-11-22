package com.projetotopicos.annamendes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetotopicos.annamendes.entity.Anotacao;

public interface AnotacaoRepository extends JpaRepository<Anotacao, Long>{

    public List<Anotacao> findByTextoContainsIgnoreCase(String texto);
    
}
