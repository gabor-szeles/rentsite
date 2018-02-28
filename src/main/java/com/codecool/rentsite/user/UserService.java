package com.codecool.rentsite.user;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void register(Map<String, String> requestParams) {
        String username = requestParams.get("username");
        String password = requestParams.get("password");
        String email = requestParams.get("email");
        String firstName = requestParams.get("firstname");
        String lastName = requestParams.get("lastname");
        if (checkRegistrationDetails(username, password, email, firstName, lastName)){
            //TODO notify the user about the issue
        }
        try {
            if(username.length() > 4 || password.length() > 4) {
                User newUser = new User(firstName, lastName, username, password, email);
                userRepository.save(newUser);
            }
        } catch (Exception e) {
            System.out.println(e);
            //TODO Error message in modal
        }

    }

    public boolean checkRegistrationDetails(String username, String password, String email, String firstname, String lastname){
        return (username.length() < 4 || password.length() < 4 || email.length() < 4 || firstname.length() < 1 || lastname.length() < 2);
    }

    public void login(Map<String, String> requestParams, HttpSession session) {
        String username = requestParams.get("username");
        String password = requestParams.get("password");
        try {
            User result = userRepository.findByUsername(username);
            String id = String.valueOf(result.getId());
            if (result.getUsername().equals(username) && result.getPassword().equals(password)) {
                session.setAttribute("userId", id);
            }
        } catch (javax.persistence.NoResultException e) {
            //TODO Error mesage in modal
        }
    }


    public boolean userExists(String username){
        try {
            User result = userRepository.findByUsername(username);
            return false;
        } catch (javax.persistence.NoResultException e){
            System.out.println(e);
            return true;
        }
    }

    public void logoutUser(HttpSession session) {
        session.removeAttribute("userId");
    }

    public int getUserId(HttpSession session) {
        int userId;
        try {
            userId = Integer.parseInt(session.getAttribute("userId").toString());
        } catch (NullPointerException|NumberFormatException e) {
            userId = -1;
        }
        return userId;
    }
}
