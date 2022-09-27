package com.projetotopicos.annamendes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projetotopicos.annamendes.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  public Usuario findByNome(String nome);
  
  @Query("select us from Usuario us where us.nome = ?1")
  public Usuario buscarPorNome(String nome);

  public List<Usuario> findByNomeContains(String nome);

  public List<Usuario> findByAutorizacoesNome(String autorizacao);

  @Query("select us from Usuario us join us.autorizacoes au where au.nome = ?1")
  public List<Usuario> buscarPorAutorizacao(String autorizacao);
    
}

