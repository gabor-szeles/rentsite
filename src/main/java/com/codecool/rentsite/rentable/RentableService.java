package com.codecool.rentsite.rentable;

import com.codecool.rentsite.rentable.category.Category;
import com.codecool.rentsite.rentable.category.CategoryDAO;
import com.codecool.rentsite.rentable.category.ItemCategory;
import com.codecool.rentsite.rentable.category.ServiceCategory;

import javax.persistence.EntityManager;
import java.util.*;

import com.codecool.rentsite.user.UserDao;
import spark.Request;
import spark.Response;


public class RentableService {

    private final RentableDAO rentableDAO;
    private final CategoryDAO categoryDAO;
    private final UserDao userDao;

    public RentableService(RentableDAO rentableDAO, CategoryDAO categoryDAO, UserDao userDao) {
        this.rentableDAO = rentableDAO;
        this.categoryDAO = categoryDAO;
        this.userDao = userDao;
    }

    public Map<String, List<Rentable>> getAllRentables() {
        Map params = new HashMap();
        List<Category> allCategories = new ArrayList<>();
        List<ItemCategory> itemCategoryList = categoryDAO.getItemCategories();
        List<ServiceCategory> serviceCategoryList = categoryDAO.getServiceCategories();
        List<Rentable> rentableList = rentableDAO.getAll();
        allCategories.addAll(itemCategoryList);
        allCategories.addAll(serviceCategoryList);
        params.put("rentableList", rentableList);
        params.put("itemCategories", itemCategoryList);
        params.put("serviceCategories", serviceCategoryList);
        params.put("categories", allCategories);
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
                break;
            case "status":
                resultList = rentableDAO.getRented(idNumber);
                break;
        }
        return resultList;
    }

    public String add(Request request, Response response, EntityManager entityManager){
        String name = request.queryParams("name");
        String description = request.queryParams("description");
        String amount = request.queryParams("price");
        String type = request.queryParams("type");
        String categoryId = request.queryParams("category");
        String userId = request.session().attribute("userId");
        Price price = new Price();
        price.setAmount(Integer.parseInt(amount));
        price.setCurrency(Currency.getInstance("EUR"));
        if (type.equals("item")){
            Item newItem = new Item();
            newItem.setName(name);
            newItem.setDescription(description);
            newItem.setPrice(price);
            newItem.setItemCategory(categoryDAO.findItemCategory(Integer.parseInt(categoryId)));
            newItem.setUser(userDao.find(Integer.parseInt(userId)));
            newItem.setStatus(Status.AVAILABLE);

            entityManager.getTransaction().begin();
            entityManager.persist(newItem);
            entityManager.getTransaction().commit();
            response.redirect("/");
            return "Item added";
        } else if (type.equals("service")){
            Service newService = new Service();
            newService.setName(name);
            newService.setDescription(description);
            newService.setPrice(price);
            newService.setServiceCategory(categoryDAO.findServiceCategory(Integer.parseInt(categoryId)));
            newService.setUser(userDao.find(Integer.parseInt(userId)));
            newService.setStatus(Status.AVAILABLE);

            entityManager.getTransaction().begin();
            entityManager.persist(newService);
            entityManager.getTransaction().commit();
            response.redirect("/");
            return "Service added";
        }
        return "";
    }
}
