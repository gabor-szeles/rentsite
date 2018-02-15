package com.codecool.rentsite.user;

import com.codecool.rentsite.SessionHandling;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class UserService {
        private final UserDao USER_DAO;

    public UserService(UserDao USER_DAO) {
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

    public JSONObject checkUser(Request request) {
        JSONObject json = new JSONObject();
        json.put("username_exist", userExists(request.queryParams("username")));
        return json;
    }


    public boolean userExists(String username){
        try {
            User result = USER_DAO.find(username);
            return false;
        } catch (javax.persistence.NoResultException e){
            System.out.println(e);
            return true;
        }
    }

    public String logoutUser(Request request, Response response) {
        request.session().removeAttribute("userId");
        response.redirect("/");
        return "";
    }
}
