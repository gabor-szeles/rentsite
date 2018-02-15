package com.codecool.rentsite;

import com.codecool.rentsite.rentable.*;
import com.codecool.rentsite.rentable.category.CategoryDAO;
import com.codecool.rentsite.reservation.ReservationDAO;
import com.codecool.rentsite.user.UserDao;
import com.codecool.rentsite.reservation.Reservation;
import com.codecool.rentsite.user.User;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Type;
import java.util.*;

public class Controller {
    private final static EntityManagerFactory ENTITY_MANAGER_FACTORY;
    private static RentableDAO rentableDAO;
    private static CategoryDAO categoryDAO;
    private static RentableService rentableService;

    static {
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("jpaexamplePU");
        rentableDAO = new RentableDAO(ENTITY_MANAGER_FACTORY);
        categoryDAO = new CategoryDAO(ENTITY_MANAGER_FACTORY);
        rentableService = new RentableService(rentableDAO, categoryDAO);
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

}
