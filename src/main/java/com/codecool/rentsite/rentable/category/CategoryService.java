package com.codecool.rentsite.rentable.category;

import com.codecool.rentsite.Utils;
import com.codecool.rentsite.rentable.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryService {

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;

    public List<Map<String, String>> getAllItemCategories(){
        return Utils.buildCategoryListModel(itemCategoryRepository.findAll());
    }
    public List<Map<String, String>> getAllServiceCategories(){
        return Utils.buildCategoryListModel(serviceCategoryRepository.findAll());
    }
}
