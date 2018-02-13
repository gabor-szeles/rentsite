package com.codecool.rentsite;

import com.codecool.rentsite.DAO.RentableDAO;
import com.codecool.rentsite.DAO.ReservationDAO;
import com.codecool.rentsite.DAO.UserDao;
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

    public static ModelAndView renderUsers(Request req, Response res) {

        Map params = new HashMap();
        UserDao userDao = new UserDao(ENTITY_MANAGER_FACTORY);
        RentableDAO rentableDAO = new RentableDAO(ENTITY_MANAGER_FACTORY);
        ReservationDAO reservationDAO = new ReservationDAO(ENTITY_MANAGER_FACTORY);

        List<Item> items = rentableDAO.executeItemQuery();
        List<Service> services = rentableDAO.executeServiceQuery();
        List<Reservation> reservations = reservationDAO.executeQuery();
        List<User> returnValues = userDao.executeQuery();

        params.put("userList", returnValues);
        params.put("itemList", items);
        params.put("serviceList", services);
        params.put("reservationList", reservations);
        return new ModelAndView(params, "/index");
    }
}
