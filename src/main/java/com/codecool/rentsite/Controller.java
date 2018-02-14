package com.codecool.rentsite;

import com.codecool.rentsite.rentable.*;
import com.codecool.rentsite.reservation.ReservationDAO;
import com.codecool.rentsite.user.UserDao;
import com.codecool.rentsite.reservation.Reservation;
import com.codecool.rentsite.user.User;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class Controller {
    private final static EntityManagerFactory ENTITY_MANAGER_FACTORY;
    private static RentableDAO rentableDAO;
    private static RentableService rentableService;

    static {
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("jpaexamplePU");
        rentableDAO = new RentableDAO(ENTITY_MANAGER_FACTORY);
        rentableService = new RentableService(rentableDAO);
    }

    public static String renderRentables(Request req, Response res) {
        Map<String, List<Rentable>> params = rentableService.getAllRentables();
        return renderTemplate(params, "index");
    }

    public static String renderTemplate(Map model, String template) {
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, template));
    }
}
