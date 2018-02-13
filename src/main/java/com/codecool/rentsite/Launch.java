package com.codecool.rentsite;


import org.apache.log4j.BasicConfigurator;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;



import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Launch {

    public static void main(String[] args) {
        Logger.getLogger("org").setLevel(Level.INFO);
        Logger.getLogger("akka").setLevel(Level.INFO);


        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);
        BasicConfigurator.configure();


        get("/", (Request req, Response res) -> new ThymeleafTemplateEngine().render(Controller.renderUsers(req, res)));

        post("/register", (Request req, Response res) ->{
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            String email = req.queryParams("email");
            SessionHandling.register(username, password, email);
            res.redirect("/");
            return "registered";
        });



    }
}
