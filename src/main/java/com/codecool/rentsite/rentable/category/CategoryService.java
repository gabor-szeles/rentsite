package com.codecool.rentsite.rentable.category;

import com.codecool.rentsite.Utils;
import com.codecool.rentsite.rentable.Item;

import java.util.List;
import java.util.Map;

public class CategoryService {
    private CategoryDAO categoryDAO;

    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public List<Map<String, String>> getAllItemCategories(){
        return Utils.buildCategoryListModel(categoryDAO.getItemCategories());
    }
    public List<Map<String, String>> getAllServiceCategories(){
        return Utils.buildCategoryListModel(categoryDAO.getServiceCategories());
    }
}
