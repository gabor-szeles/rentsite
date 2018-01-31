package com.codecool.rentsite;

import com.codecool.rentsite.review.Review;
import com.codecool.rentsite.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.util.List;

public class Launch {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaexamplePU");
        EntityManager em = emf.createEntityManager();



        String query = "SELECT r FROM Review r";



        Query query1 = em.createQuery(query);
        List<Review> results = query1.getResultList();

        System.out.println(results);
        em.close();
        emf.close();
    }
}
