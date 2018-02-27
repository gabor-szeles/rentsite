package com.codecool.rentsite;

import com.codecool.rentsite.rentable.*;
import com.codecool.rentsite.rentable.category.CategoryService;
import com.codecool.rentsite.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
            userId =(int) session.getAttribute("userId");
        } catch (NullPointerException|NumberFormatException e) {
            userId = -1;
        }
        model.addAttribute("params", rentableService.getAllRentables(userId));
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



}
