package com.codecool.rentsite;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;



import static spark.Spark.*;

public class Launch {

    public static void main(String[] args) {
        Logger.getLogger("org").setLevel(Level.INFO);
        Logger.getLogger("akka").setLevel(Level.INFO);


        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);
        BasicConfigurator.configure();


        get("/", Controller::renderRentables);
        post("/filter", Controller::renderFilteredIndex);

        post("/register", Controller::register);

        post("/login", Controller::login);

        post("/username", "application/json", Controller::checkUser);

        get("/logout", Controller::logout);

    }
}
