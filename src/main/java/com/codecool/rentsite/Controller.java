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

import java.util.*;

public class Controller {

    public static ModelAndView renderUsers(Request req, Response res) {
        Map params = new HashMap();
        UserDao userDao = UserDao.getInstance();
        RentableDAO rentableDAO = RentableDAO.getInstance();
        ReservationDAO reservationDAO = ReservationDAO.getInstance();

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
