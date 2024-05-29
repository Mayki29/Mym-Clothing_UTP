package com.utp.algoritmos.mymclothing.services;

import java.util.List;

import com.utp.algoritmos.mymclothing.models.Producto;

public interface ProductoService {
    List<Producto> findAll();

    void save(Producto producto);

    Producto findById(Long id);

    void delete(Long id);
}
