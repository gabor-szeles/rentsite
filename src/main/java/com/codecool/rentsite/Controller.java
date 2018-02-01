package com.codecool.rentsite;

import com.codecool.rentsite.jdbc.JDBC;
import com.codecool.rentsite.user.User;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.jws.soap.SOAPBinding;
import java.util.*;

public class Controller {

    public static ModelAndView renderProducts(Request req, Response res) {
        Map params = new HashMap();
        JDBC jdbc = JDBC.getInstance();
        List<User> returnValues = jdbc.executeQuery();
        params.put("userList", returnValues);
        System.out.println("HEREEEEEEEEEE: " + returnValues.get(0).getEmail());
        return new ModelAndView(params, "/index");
    }
}
