package com.codecool.rentsite.user;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDao implements UserDAOInterface {

    private static EntityManager entityManager;

    public UserDao(EntityManagerFactory emf){
        this.entityManager = emf.createEntityManager();
    }

    public List<User> executeQuery(){
        TypedQuery<User> query = entityManager.createNamedQuery("user.all",User.class);
        List<User> result = query.getResultList();
        return result;
    }

    @Override
    public void add(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public User find(int id) {
        return null;
    }
}
