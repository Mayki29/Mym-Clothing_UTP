package com.utp.algoritmos.mymclothing.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.utp.algoritmos.mymclothing.models.Venta;

import jakarta.transaction.Transactional;


public interface VentaRepository extends CrudRepository<Venta,Long> {
    //List<Venta> findAll();
}
