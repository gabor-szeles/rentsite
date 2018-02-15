package com.codecool.rentsite;

import com.codecool.rentsite.rentable.*;
import com.codecool.rentsite.rentable.category.CategoryDAO;
import com.codecool.rentsite.rentable.category.CategoryService;
import com.codecool.rentsite.user.UserDao;
import com.codecool.rentsite.user.UserService;

import org.json.JSONObject;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.security.PublicKey;
import java.util.*;

public class Controller {

    private final static EntityManagerFactory ENTITY_MANAGER_FACTORY;
    private static EntityManager entityManager;
    private static RentableDAO rentableDAO;
    private static CategoryDAO categoryDAO;
    private static UserDao userDao;
    private static RentableService rentableService;
    private static UserService userService;
    private static CategoryService categoryService;

    static {
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("jpaexamplePU");
        entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        rentableDAO = new RentableDAO(ENTITY_MANAGER_FACTORY);
        categoryDAO = new CategoryDAO(ENTITY_MANAGER_FACTORY);
        userDao = new UserDao(ENTITY_MANAGER_FACTORY);
        rentableService = new RentableService(rentableDAO, categoryDAO, userDao);
        userService = new UserService(ENTITY_MANAGER_FACTORY, userDao);
        categoryService = new CategoryService(categoryDAO);
    }

    public static String renderRentables(Request req, Response res) {
        Map<String, List<Rentable>> params = rentableService.getAllRentables();
        return Utils.renderTemplate(params, "index");
    }

    public static String renderFilteredIndex(Request request, Response response) {
        String id = request.body();
        List<Map<String, String>> params = Utils.productModel(rentableService.getUpdatedData(id));
        return Utils.toJson(params);
    }

    public static String addNewItem(Request request, Response response){
        return rentableService.add(request, response, ENTITY_MANAGER_FACTORY.createEntityManager());
    }

    public static String register(Request request, Response response) {
        return userService.register(request, response);
    }

    public static String login(Request request, Response response){
        SessionHandling.recognizeClient(request, response);
        return userService.login(request, response, entityManager);
    }

    public static JSONObject checkUser(Request request, Response response) {
        return userService.checkUser(request,entityManager);
    }

    public static String logout(Request request, Response response) {
        return userService.logoutUser(request, response);
    }

    public static String getItemCategories(Request request, Response response){
        return Utils.toJson(categoryService.getAllItemCategories());
    }

    public static String getServiceCategories(Request request, Response response){
        return Utils.toJson(categoryService.getAllServiceCategories());
    }
}
