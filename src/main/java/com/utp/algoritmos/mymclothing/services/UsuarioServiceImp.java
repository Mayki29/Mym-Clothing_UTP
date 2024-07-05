package com.utp.algoritmos.mymclothing.services;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utp.algoritmos.mymclothing.models.Usuario;
import com.utp.algoritmos.mymclothing.repositories.UsuarioRepository;


@Service
public class UsuarioServiceImp implements UsuarioService{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void delete(Long id) {
        usuarioRepository.delete(id);
        
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        usuario.setRol(2L);
        usuario.setFechaCreacion(LocalDateTime.now());
        usuario.setPassword(DigestUtils.sha256Hex(usuario.getPassword())); 
        Usuario savedUser = usuarioRepository.save(usuario);
        if(savedUser == null) return null;
        savedUser.setPassword(null);
        return savedUser;
    }

    @Override
    public Usuario login(Usuario usuario) {
        usuario.setPassword(DigestUtils.sha256Hex(usuario.getPassword()));
        Usuario logedUser = usuarioRepository.login(usuario);
        if(logedUser == null)return null;
        logedUser.setPassword(null);
        return logedUser;
    }

}
