package com.codecool.rentsite.DAO;

import com.codecool.rentsite.user.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDao {

    private static UserDao userDaoInstance  = null;

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaexamplePU");
    private final EntityManager em = emf.createEntityManager();

    private UserDao(){}

    public static UserDao getInstance() {
        if(userDaoInstance == null) {
            userDaoInstance = new UserDao();
        }
        return userDaoInstance;
    }

    public List<User> executeQuery(){
        TypedQuery<User> query = em.createNamedQuery("user.all",User.class);
        List<User> result = query.getResultList();
        return result;
    }
}
