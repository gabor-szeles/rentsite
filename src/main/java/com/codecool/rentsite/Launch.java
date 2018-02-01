package com.codecool.rentsite;


import com.codecool.rentsite.review.Review;
import org.apache.log4j.BasicConfigurator;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;


import javax.persistence.*;

import java.util.List;

import static spark.Spark.*;

public class Launch {

    public static void main(String[] args) {
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);
        BasicConfigurator.configure();



        get("/", (Request req, Response res) -> new ThymeleafTemplateEngine().render(Controller.renderProducts(req, res)));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaexamplePU");
        EntityManager em = emf.createEntityManager();


        String query = "SELECT r FROM ReservationReview r";
        Query query1 = em.createQuery(query);
        List<Review> results = query1.getResultList();

        System.out.println(results);
        em.close();
        emf.close();
    }
}
