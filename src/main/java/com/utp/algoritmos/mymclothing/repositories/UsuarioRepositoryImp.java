package com.utp.algoritmos.mymclothing.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.utp.algoritmos.mymclothing.models.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UsuarioRepositoryImp implements UsuarioRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void delete(Long id) {
        Usuario user = entityManager.find(Usuario.class, id);
        entityManager.remove(user);
        
    }

    @Override
    public List<Usuario> findAll() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Usuario findById(Long id) {
        return entityManager.find(Usuario.class, id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return entityManager.merge(usuario);
    }

    @Override
    public Usuario login(Usuario usuario) {
        String query = "FROM Usuario WHERE email = :email";
        List<Usuario> users = entityManager.createQuery(query).setParameter("email", usuario.getEmail()).getResultList();
        if(users.isEmpty()){
            return null;
        }
        String password = users.get(0).getPassword();
        if(usuario.getPassword().equals(password)){
            return users.get(0);
        }
        return null;
    }
        
}
