package com.codecool.rentsite;

import com.codecool.rentsite.jdbc.JDBC;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {

    public static ModelAndView renderProducts(Request req, Response res) {
        Map params = new HashMap();
        JDBC jdbc = new JDBC();
        List returnValues = jdbc.getReturnValues();
        params.put("values", returnValues);
        return new ModelAndView(params, "/index");
    }
}
