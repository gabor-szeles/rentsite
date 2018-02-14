package com.codecool.rentsite;

import com.codecool.rentsite.rentable.Rentable;
import com.codecool.rentsite.rentable.RentableDAO;
import com.codecool.rentsite.reservation.ReservationDAO;
import com.codecool.rentsite.user.UserDao;
import com.codecool.rentsite.reservation.Reservation;
import com.codecool.rentsite.user.User;
import com.codecool.rentsite.user.UserService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class Controller {
    private final static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("jpaexamplePU");
    private final static UserDao USER_DAO = new UserDao(ENTITY_MANAGER_FACTORY);
    private final static UserService USER_SERVICE = new UserService(ENTITY_MANAGER_FACTORY, USER_DAO);
    private final static EntityManager ENTITY_MANAGER = ENTITY_MANAGER_FACTORY.createEntityManager();
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
        return USER_SERVICE.register(request, response);
    }

    public static String login(Request request, Response response){
        SessionHandling.recognizeClient(request, response);
        return USER_SERVICE.login(request, response, ENTITY_MANAGER);
    }
}
