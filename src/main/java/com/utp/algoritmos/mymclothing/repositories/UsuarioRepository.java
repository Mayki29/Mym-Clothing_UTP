package com.utp.algoritmos.mymclothing.repositories;

import java.util.List;

import com.utp.algoritmos.mymclothing.models.Usuario;

public interface UsuarioRepository {
    List<Usuario> findAll();
    Usuario save(Usuario usuario);
    Usuario findById(Long id);
    void delete(Long id);
    Usuario login(Usuario usuario);
}
