package com.codecool.rentsite;

import com.codecool.rentsite.rentable.Rentable;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Request;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static Map<String, String> parseJson(Request request) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> out = gson.fromJson(request.body(), type);
        return out;
    }

    public static String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static String renderTemplate(Map model, String template) {
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, template));
    }

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
}
