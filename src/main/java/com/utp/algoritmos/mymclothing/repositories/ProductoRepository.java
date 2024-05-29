package com.utp.algoritmos.mymclothing.repositories;

import java.util.List;

import com.utp.algoritmos.mymclothing.models.Producto;

public interface ProductoRepository {
    List<Producto> findAll();

    void save(Producto producto);

    Producto findById(Long id);

    void delete(Long id);
}
