package com.codecool.rentsite;

import com.codecool.rentsite.user.User;
import com.codecool.rentsite.user.UserDao;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Map;

public class SessionHandling {

    public final static int guestId = 0;
    private final UserDao userDao;


    public SessionHandling(UserDao userDao) {
        this.userDao = userDao;
    }

    public void register(String username, String password, String email){
        if(username.length() > 4 || password.length() > 4) {
            userDao.add(username, password, email);
        }

    }


    public static int recognizeClient(Request req, Response res){
        if( req.cookie("clientId") == null){
            res.cookie("clientId", Integer.toString(guestId));
        }
        try {
            return Integer.parseInt(req.cookie("clientId"));
        } catch (NumberFormatException e){
            return guestId;
        }
    }

    public static void logout(Response res){
        res.cookie("clientId", Integer.toString(guestId));
    }

    /*public static boolean tryLogin(String username, String password, Response res){
        Map <String, String> userCredentials = SelectQueries.getUserCredentials(username);
        String dbUsername = "";
        dbUsername = userCredentials.get("username");
        String dbPassword = "";
        dbPassword = userCredentials.get("password");
        String dbUserId = userCredentials.get("id");
        if(checkLogin(password, password)){
            res.cookie("clientId", dbUserId);
            return true;
        }
        res.cookie("clientId", Integer.toString(guestId));
        return false;
    }
    */

    public static boolean checkLogin(String dbpassword, String password){

        if(dbpassword.equals(password)){
            return true;
        }
        return false;
    }

}
