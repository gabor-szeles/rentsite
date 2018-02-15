package com.codecool.rentsite;

import com.codecool.rentsite.rentable.*;
import com.codecool.rentsite.rentable.category.CategoryDAO;
import com.codecool.rentsite.user.UserDao;
import com.codecool.rentsite.user.UserService;

import org.json.JSONObject;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class Controller {

    private final static EntityManagerFactory ENTITY_MANAGER_FACTORY;
    private static EntityManager entityManager;
    private static RentableDAO rentableDAO;
    private static CategoryDAO categoryDAO;
    private static UserDao userDao;
    private static RentableService rentableService;
    private static UserService userService;

    static {
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("jpaexamplePU");
        entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        rentableDAO = new RentableDAO(ENTITY_MANAGER_FACTORY);
        categoryDAO = new CategoryDAO(ENTITY_MANAGER_FACTORY);
        userDao = new UserDao(ENTITY_MANAGER_FACTORY);
        rentableService = new RentableService(rentableDAO, categoryDAO);
        userService = new UserService(userDao);
    }

    public static String renderRentables(Request req, Response res) {
        int userId;
        try {
            userId = Integer.parseInt(req.session().attribute("userId"));
        } catch (NullPointerException|NumberFormatException e) {
            userId = -1;
        }
        Map<String, List<Rentable>> params = rentableService.getAllRentables(userId);
        return Utils.renderTemplate(params, "index");
    }

    public static String renderFilteredIndex(Request request, Response response) {
        String id = request.body();
        List<Map<String, String>> params = Utils.productModel(rentableService.getUpdatedData(id));
        return Utils.toJson(params);
    }

    public static String register(Request request, Response response) {
        return userService.register(request, response);
    }

    public static String login(Request request, Response response){
        SessionHandling.recognizeClient(request, response);
        return userService.login(request, response, entityManager);
    }

    public static JSONObject checkUser(Request request, Response response) {
        return userService.checkUser(request);
    }

    public static String logout(Request request, Response response) {
        return userService.logoutUser(request, response);
    }
}
