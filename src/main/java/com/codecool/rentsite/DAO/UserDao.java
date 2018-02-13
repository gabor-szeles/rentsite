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
    private static final EntityManager em = emf.createEntityManager();

    private UserDao(){}

    public static UserDao getInstance() {
        if(userDaoInstance == null) {
            userDaoInstance = new UserDao();
        }
        return userDaoInstance;
    }

    public static void addUser(String username, String password, String email){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public List<User> executeQuery(){
        TypedQuery<User> query = em.createNamedQuery("user.all",User.class);
        List<User> result = query.getResultList();
        return result;
    }
}
