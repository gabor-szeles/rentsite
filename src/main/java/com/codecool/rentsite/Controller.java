package com.codecool.rentsite;

import com.codecool.rentsite.rentable.*;
import com.codecool.rentsite.rentable.category.CategoryService;
import com.codecool.rentsite.reservation.ReservationService;
import com.codecool.rentsite.review.ReviewService;
import com.codecool.rentsite.reservation.Reservation;
import com.codecool.rentsite.review.UserReview;
import com.codecool.rentsite.user.User;
import com.codecool.rentsite.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReservationService reservationService;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderIndex(Model model, HttpSession session) {
        int userId = userService.getUserId(session);
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

    @RequestMapping(value = "/rentable/{id}", method = RequestMethod.GET)
    public String renderRentablePage(@PathVariable(value = "id") String id, Model model, HttpSession session){
        int userId = userService.getUserId(session);
        model.addAttribute("userId", userId);
        model.addAttribute("rentableDetails", rentableService.getRentableById(id));
        model.addAttribute("reviews", reviewService.getAllReviewsByRentableId(id));
        model.addAttribute("url", rentableService.getRentableUrl(id));
        return "rentableTemplate";
    }

    @RequestMapping(value = "/rent/{id}", method = RequestMethod.GET)
    public String rentItem(@PathVariable(value = "id") String id, HttpSession session) {
        reservationService.createNewReservation(id, session.getAttribute("userId").toString());
        return "redirect:/rentable/{id}";
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
        model.addAttribute("receivingReview", id);
        return "userReview";
    }

    @RequestMapping(value = "/user-review/{id}", method = RequestMethod.POST)
    public String addUserReview(@PathVariable(value = "id") String receiverId,
                                @RequestParam Map<String, String> reqPar,
                                HttpSession session){
        String writerId = session.getAttribute("userId").toString();
        reviewService.add(reqPar, receiverId, writerId);
        return "redirect:/user-page/" + receiverId;
    }

    @RequestMapping(value = "/rentable-review/{id}", method = RequestMethod.POST)
    public String addRentableReview(@PathVariable(value = "id") String rentableID,
                                    @RequestParam Map<String, String> reqPar,
                                    HttpSession session) {
        String description = reqPar.get("description");
        int rate =Integer.parseInt(reqPar.get("rate"));
        System.out.println("desc: "+ description+" rate "+ rate);
        return "TODOSHIT";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String handleFileUpload(@RequestBody Map<String, String> fileData) {
        String itemOrServiceId = fileData.get("id");
        String pictureUrl = fileData.get("url");
        rentableService.updateRentableUrl(itemOrServiceId, pictureUrl);
        return "redirect:/rentable/" + itemOrServiceId;
    }

}
