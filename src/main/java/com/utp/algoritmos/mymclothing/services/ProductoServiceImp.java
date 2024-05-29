package com.utp.algoritmos.mymclothing.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utp.algoritmos.mymclothing.models.Producto;
import com.utp.algoritmos.mymclothing.repositories.ProductoRepository;

@Service
public class ProductoServiceImp implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void delete(Long id) {
        productoRepository.delete(id);        
    }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public void save(Producto producto) {
        productoRepository.save(producto);
    }

}
