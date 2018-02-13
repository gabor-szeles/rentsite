package com.codecool.rentsite;

import com.codecool.rentsite.rentable.Rentable;
import com.codecool.rentsite.rentable.RentableDAO;
import com.codecool.rentsite.reservation.ReservationDAO;
import com.codecool.rentsite.user.UserDao;
import com.codecool.rentsite.rentable.Item;
import com.codecool.rentsite.rentable.Service;
import com.codecool.rentsite.reservation.Reservation;
import com.codecool.rentsite.user.User;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class Controller {
    private final static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("jpaexamplePU");
    private final static UserDao USER_DAO = new UserDao(ENTITY_MANAGER_FACTORY);

    public static ModelAndView renderUsers(Request req, Response res) {

        Map params = new HashMap();
        UserDao userDao = new UserDao(ENTITY_MANAGER_FACTORY);
        RentableDAO rentableDAO = new RentableDAO(ENTITY_MANAGER_FACTORY);
        ReservationDAO reservationDAO = new ReservationDAO(ENTITY_MANAGER_FACTORY);

        List<Reservation> reservations = reservationDAO.getAll();
        List<User> returnValues = userDao.executeQuery();
        List<Rentable> rentableList = rentableDAO.getAll();

        params.put("userList", returnValues);
        params.put("reservationList", reservations);
        params.put("rentableList", rentableList);
        return new ModelAndView(params, "/index");
    }

    public static String register(Request request, Response response) {
        String username = request.queryParams("username");
        String password = request.queryParams("password");
        String email = request.queryParams("email");
        SessionHandling sessionHandling = new SessionHandling(USER_DAO);
        sessionHandling.register(username, password, email);
        response.redirect("/");
        return "registered";
    }
}
