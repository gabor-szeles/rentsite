package com.codecool.rentsite.user;

import com.codecool.rentsite.user.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDao implements UserDAOInterface {

    private EntityManager entityManager;

    public UserDao(EntityManagerFactory emf){
        this.entityManager = emf.createEntityManager();
    }

    public List<User> executeQuery(){
        TypedQuery<User> query = entityManager.createNamedQuery("user.all",User.class);
        List<User> result = query.getResultList();
        return result;
    }

    @Override
    public User add() {
        return null;
    }

    @Override
    public User find(int id) {
        return null;
    }
}
