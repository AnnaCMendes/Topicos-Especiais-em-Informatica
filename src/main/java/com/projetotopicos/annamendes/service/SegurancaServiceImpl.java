package com.projetotopicos.annamendes.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projetotopicos.annamendes.entity.Autorizacao;
import com.projetotopicos.annamendes.entity.Usuario;
import com.projetotopicos.annamendes.repository.UsuarioRepository;
import com.projetotopicos.annamendes.repository.AutorizacaoRepository;

@Service
public class SegurancaServiceImpl implements SegurancaService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private AutorizacaoRepository autorizacaoRepo;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario novoUsuario(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    @Override
    public Usuario novoUsuario(String nome, String senha) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(senha);
        return novoUsuario(usuario);
    }

    @Override
    @Transactional
    public Usuario novoUsuario(String nome, String senha, String autorizacao) {
        Autorizacao aut = autorizacaoRepo.findByNome(autorizacao);
        if(aut == null) {
            aut = new Autorizacao();
            aut.setNome(autorizacao);
            autorizacaoRepo.save(aut);
        }
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(senha);
        usuario.setAutorizacoes(new HashSet<Autorizacao>());
        usuario.getAutorizacoes().add(aut);
        return novoUsuario(usuario);
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public List<Usuario> todosUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Usuario buscarPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepo.findById(id);
        if(usuarioOptional.isPresent()) {
            return usuarioOptional.get();
        }
        throw new RuntimeException("Usuario nao encontrado!");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByNome(username);
        if (usuario == null) {
          throw new UsernameNotFoundException("Usuário " + username + " não encontrado!");
        }
        return User.builder().username(username).password(usuario.getSenha())
            .authorities(usuario.getAutorizacoes().stream()
                .map(Autorizacao::getNome).collect(Collectors.toList())
                .toArray(new String[usuario.getAutorizacoes().size()]))
            .build();
    }
    
}
