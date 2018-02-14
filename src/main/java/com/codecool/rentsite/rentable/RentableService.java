package com.codecool.rentsite.rentable;

import com.codecool.rentsite.rentable.category.CategoryDAO;
import com.codecool.rentsite.rentable.category.ItemCategory;
import com.codecool.rentsite.rentable.category.ServiceCategory;

import java.util.*;

public class RentableService {

    private final RentableDAO rentableDAO;
    private final CategoryDAO categoryDAO;

    public RentableService(RentableDAO rentableDAO, CategoryDAO categoryDAO) {
        this.rentableDAO = rentableDAO;
        this.categoryDAO = categoryDAO;
    }

    public Map<String, List<Rentable>> getAllRentables() {
        Map params = new HashMap();
        List<ItemCategory> itemCategoryList = categoryDAO.getItemCategories();
        List<ServiceCategory> serviceCategoryList = categoryDAO.getServiceCategories();
        List<Rentable> rentableList = rentableDAO.getAll();
        params.put("rentableList", rentableList);
        params.put("itemCategories", itemCategoryList);
        params.put("serviceCategories", serviceCategoryList);
        return params;
    }

    public List<? extends Rentable> getUpdatedData(String id) {
        List<? extends Rentable> resultList = new ArrayList<>();
        String[] idParts = id.replace("\"", "").split("_");
        String type = idParts[0];
        int idNumber = Integer.parseInt(idParts[1]);
        switch (type) {
            case "item":
                resultList = rentableDAO.getByItemCategory(idNumber);
                break;
            case "allitems":
                resultList = rentableDAO.getAllItems();
                break;
            case "service":
                resultList = rentableDAO.getByServiceCategory(idNumber);
                break;
            case "allservices":
                resultList = rentableDAO.getAllServices();
        }
        return resultList;
    }
}
