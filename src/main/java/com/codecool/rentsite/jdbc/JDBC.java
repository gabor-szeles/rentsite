package com.codecool.rentsite.jdbc;

import com.codecool.rentsite.user.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class JDBC {

    private static JDBC JDBCINSTANCE  = null;

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaexamplePU");
    private final EntityManager em = emf.createEntityManager();

    private JDBC(){}

    public static JDBC getInstance() {
        if(JDBCINSTANCE == null) {
            JDBCINSTANCE = new JDBC();
        }
        return JDBCINSTANCE;
    }

    public List<User> executeQuery(){
        TypedQuery<User> query = em.createNamedQuery("user.all",User.class);
        List<User> result = query.getResultList();
        return result;
   }



}
