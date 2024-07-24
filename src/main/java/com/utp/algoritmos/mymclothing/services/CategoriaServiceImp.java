package com.utp.algoritmos.mymclothing.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utp.algoritmos.mymclothing.models.Categoria;
import com.utp.algoritmos.mymclothing.repositories.CategoriaRepository;

@Service
public class CategoriaServiceImp implements CategoriaService{

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> findAll() {
        return (List<Categoria>)categoriaRepository.findAll();
    }

}
