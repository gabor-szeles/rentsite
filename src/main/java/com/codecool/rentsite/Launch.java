package com.codecool.rentsite;


import com.codecool.rentsite.review.Review;
import org.apache.log4j.BasicConfigurator;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;


import javax.persistence.*;

import java.util.List;

import static spark.Spark.*;

public class Launch {

    public static void main(String[] args) {
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);
        BasicConfigurator.configure();



        get("/", (Request req, Response res) -> new ThymeleafTemplateEngine().render(Controller.renderProducts(req, res)));
    }
}
