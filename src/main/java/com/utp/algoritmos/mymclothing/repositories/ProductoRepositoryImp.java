package com.utp.algoritmos.mymclothing.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.utp.algoritmos.mymclothing.models.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class ProductoRepositoryImp implements ProductoRepository{


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void delete(Long id) {
        Producto producto = entityManager.find(Producto.class, id);
        entityManager.remove(producto);
    }

    @Override
    public List<Producto> findAll() {
        String query = "FROM Producto";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Producto findById(Long id) {
        return entityManager.find(Producto.class, id);
    }

    @Override
    public void save(Producto producto) {
        entityManager.merge(producto);
    }

    public void actualizar(Long id, Integer cantidad){
        Producto producto = entityManager.find(Producto.class, id);
        producto.setStock(producto.getStock() - cantidad);
        entityManager.merge(producto);
    }

}
