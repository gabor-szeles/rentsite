package com.codecool.rentsite;

import com.codecool.rentsite.rentable.*;
import com.codecool.rentsite.rentable.category.CategoryService;
import com.codecool.rentsite.reservation.Reservation;
import com.codecool.rentsite.review.UserReview;
import com.codecool.rentsite.user.User;
import com.codecool.rentsite.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.util.*;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private RentableService rentableService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderIndex(Model model, HttpSession session) {
        int userId;
        try {
            userId = Integer.parseInt(session.getAttribute("userId").toString());
        } catch (NullPointerException|NumberFormatException e) {
            userId = -1;
        }
        Map<String, List<Rentable>> data = rentableService.getAllRentables(userId);
        model.addAttribute("rentableList", data.get("rentableList"));
        model.addAttribute("itemCategories", data.get("itemCategories"));
        model.addAttribute("serviceCategories", data.get("serviceCategories"));
        model.addAttribute("userId", userId);
        return "index";
    }

    @RequestMapping(value = "/new-item", method = RequestMethod.POST)
    public String addNewItem(@RequestParam Map<String, String> reqPar, HttpSession session) {
        String id = session.getAttribute("userId").toString();
        rentableService.add(reqPar, id);
        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam Map<String, String> reqPar) {
        userService.register(reqPar);
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam Map<String, String> reqPar, HttpSession session){
        userService.login(reqPar, session);
        return "redirect:/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        userService.logoutUser(session);
        return "redirect:/";
    }


    @RequestMapping(value = "/user-page/{id}", method = RequestMethod.GET)
    public String renderUserPage(@PathVariable(value = "id") String id, Model model, HttpSession session){
        User user = userService.getUserById(Long.parseLong(id));
        String username = user.getUsername();
        Set<Rentable> userItems = user.getRentableSet();
        System.out.println(userItems);
        Set<Reservation> userReservations = user.getReservationSet();
        Set<UserReview> userReviews = user.getRecievedReviews();
        String loggedInUserId = session.getAttribute("userId").toString();
        model.addAttribute("userName", username);
        model.addAttribute("userItems", userItems);
        model.addAttribute("userReservations", userReservations);
        model.addAttribute("userReviews", userReviews);
        model.addAttribute("loggedInUserId", loggedInUserId);
        return "userReview";
    }

}
