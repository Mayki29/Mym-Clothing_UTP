package com.utp.algoritmos.mymclothing.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.utp.algoritmos.mymclothing.models.Venta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class VentaRepositoryImp{

    @PersistenceContext
    EntityManager entityManager;

    // @Override
    // public List<Venta> findAll() {
    //     String query = "FROM Venta";
    //     return entityManager.createQuery(query).getResultList();
    // }
    
}
