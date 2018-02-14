package com.codecool.rentsite.user;

import com.codecool.rentsite.SessionHandling;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.codecool.rentsite.reservation.Reservation;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import spark.Request;
import spark.Response;

import java.util.List;

public class UserService {
    private final EntityManagerFactory ENTITY_MANAGER_FACTORY;
    private final UserDao USER_DAO;

    public UserService(EntityManagerFactory ENTITY_MANAGER_FACTORY, UserDao USER_DAO) {
        this.ENTITY_MANAGER_FACTORY = ENTITY_MANAGER_FACTORY;
        this.USER_DAO = USER_DAO;
    }

    public String register(Request request, Response response) {
        String username = request.queryParams("username");
        String password = request.queryParams("password");
        String email = request.queryParams("email");
        String firstName = request.queryParams("firstname");
        String lastName = request.queryParams("lastname");
        if (checkRegistrationDetails(username, password, email, firstName, lastName)){
            response.redirect("/");
            return "u fuked up m8";
            //TODO notify the user about the issue
        }
        SessionHandling sessionHandling = new SessionHandling(USER_DAO);
        try {
            sessionHandling.register(username, password, email, firstName, lastName);
        } catch (Exception e) {
            System.out.println(e);
            return "User already exists";
            //TODO Error message in modal
        }
        response.redirect("/");
        return "Registered";
    }

    public boolean checkRegistrationDetails(String username, String password, String email, String firstname, String lastname){
        return (username.length() < 4 || password.length() < 4 || email.length() < 4 || firstname.length() < 1 || lastname.length() < 2);
    }

    public String login(Request request, Response response, EntityManager entityManager) {
        String username = request.queryParams("username");
        String password = request.queryParams("password");
        TypedQuery<User> query = entityManager.createNamedQuery("user.getUser", User.class);
        query.setParameter("username", username);
        try {
            User result = query.getSingleResult();
            String id = String.valueOf(result.getId());
            if (result.getUsername().equals(username) && result.getPassword().equals(password)) {
                request.session().attribute("userId", id);
                response.redirect("/");
                return "Logged in";
            }
        } catch (javax.persistence.NoResultException e) {
            response.redirect("/");
            return "u messed up";
            //TODO Error mesage in modal
        }
        return "";
    }
}
