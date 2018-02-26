package com.codecool.rentsite;

import com.codecool.rentsite.rentable.Rentable;
import com.codecool.rentsite.rentable.category.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {



    public static List<Map<String, String>> productModel(List<? extends Rentable> rentables) {
        List<Map<String, String>> model = new ArrayList<>();
        for (Rentable rentable : rentables) {
            Map<String, String> currentRentables = new HashMap<>();
            currentRentables.put("name", String.valueOf(rentable.getName()));
            currentRentables.put("description", rentable.getDescription());
            currentRentables.put("User", rentable.getUser().getUsername());
            currentRentables.put("price", rentable.getPrice().getFullPrice());
            currentRentables.put("status", rentable.getStatus().toString());
            model.add(currentRentables);
        }
        return model;
    }

    public static List<Map<String, String>> buildCategoryListModel(List<? extends Category> categories) {
        List<Map<String, String>> model = new ArrayList<>();
        for (Category category : categories) {
            Map<String, String> currentCategory = new HashMap<>();
            currentCategory.put("name", String.valueOf(category.getName()));
            currentCategory.put("id", String.valueOf(category.getId()));
            model.add(currentCategory);
        }
        return model;
    }
}
