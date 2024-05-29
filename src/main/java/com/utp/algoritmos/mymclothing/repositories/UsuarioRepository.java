package com.utp.algoritmos.mymclothing.repositories;

import java.util.List;

import com.utp.algoritmos.mymclothing.models.Usuario;

public interface UsuarioRepository {
    List<Usuario> findAll();
    void save(Usuario usuario);
    Usuario findById(Long id);
    void delete(Long id);
    Usuario login(Usuario usuario);
}
