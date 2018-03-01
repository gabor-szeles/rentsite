package com.codecool.rentsite.api;

import com.codecool.rentsite.Utils;
import com.codecool.rentsite.rentable.RentableService;
import com.codecool.rentsite.rentable.category.CategoryService;
import com.codecool.rentsite.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ControllerAPI {

    @Autowired
    RentableService rentableService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @PostMapping("/api/filter")
    public ResponseEntity renderFilteredIndex(HttpServletRequest request, HttpSession session) {
        String id = null;
        String loggedInUser;
        try {
            id = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            loggedInUser = session.getAttribute("userId").toString();
        } catch (NullPointerException e){
            loggedInUser = "-1";
        }
        List<Map<String, String>> params = Utils.productModel(rentableService.getUpdatedData(id), loggedInUser);
        return new ResponseEntity(params, HttpStatus.OK);
    }

    @PostMapping("/api/username")
    public ResponseEntity checkUser(HttpServletRequest request) {
        String userName = null;
        try {
            userName = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(userService.userExists(userName), HttpStatus.OK);
    }

    @GetMapping("/api/get-service-categories")
    public ResponseEntity getServiceCategories() {
        return new ResponseEntity(categoryService.getAllServiceCategories(), HttpStatus.OK);
    }

    @GetMapping("/api/get-item-categories")
    public ResponseEntity getItemCategories() {
        return new ResponseEntity(categoryService.getAllItemCategories(), HttpStatus.OK);
    }


}

