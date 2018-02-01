package com.codecool.rentsite;

import com.codecool.rentsite.DAO.UserDao;
import com.codecool.rentsite.user.User;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

public class Controller {

    public static ModelAndView renderUsers(Request req, Response res) {
        Map params = new HashMap();
        UserDao userDao = UserDao.getInstance();
        List<User> returnValues = userDao.executeQuery();
        params.put("userList", returnValues);
        return new ModelAndView(params, "/index");
    }
}
