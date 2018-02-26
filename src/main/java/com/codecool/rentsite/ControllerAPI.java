package com.codecool.rentsite;

import com.codecool.rentsite.rentable.RentableService;
import com.codecool.rentsite.rentable.category.CategoryService;
import com.codecool.rentsite.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ControllerAPI {

    @Autowired
    RentableService rentableService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/filter")
    public ResponseEntity renderFilteredIndex(@RequestParam(value = "id") String id) {
        List<Map<String, String>> params = Utils.productModel(rentableService.getUpdatedData(id));
        return new ResponseEntity(params, HttpStatus.OK);
    }

    @GetMapping("/username")
    public ResponseEntity checkUser(@RequestParam(value = "username") String userName) {
        return new ResponseEntity(userService.userExists(userName), HttpStatus.OK);
    }

    @GetMapping("/get-service-categories")
    public ResponseEntity getServiceCategories() {
        return new ResponseEntity(categoryService.getAllServiceCategories(), HttpStatus.OK);
    }

    @GetMapping("/get-item-categories")
    public ResponseEntity getItemCategories() {
        return new ResponseEntity(categoryService.getAllItemCategories(), HttpStatus.OK);
    }


}

