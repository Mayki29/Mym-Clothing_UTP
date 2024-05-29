package com.utp.algoritmos.mymclothing.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.utp.algoritmos.mymclothing.models.Usuario;
import com.utp.algoritmos.mymclothing.services.UsuarioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class HomeControlller {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/users")
    public List<Usuario> getAllUsers() {
        return usuarioService.findAll();
    }

    @GetMapping("/user/{id}")
    public Usuario getUserbyId(@PathVariable Long id) {
        return usuarioService.findById(id);
    }
    
    @PostMapping("/user")
    public void saveUser(@RequestBody Usuario usuario) {
        usuarioService.save(usuario);
    }
    @DeleteMapping("/user/{id}")
    public void removeUser(@PathVariable Long id) {
        usuarioService.delete(id);
    }
}
