package com.codecool.rentsite.rentable;


import com.codecool.rentsite.rentable.category.*;


import java.util.*;

import com.codecool.rentsite.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;



@org.springframework.stereotype.Service
public class RentableService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;

    @Autowired
    private UserRepository userRepository;


    public RentableService(ServiceRepository serviceRepository, ItemCategoryRepository itemCategoryRepository, ServiceCategoryRepository serviceCategoryRepository, UserRepository userRepository, ItemRepository itemRepository) {
        this.serviceRepository = serviceRepository;
        this.itemCategoryRepository = itemCategoryRepository;
        this.serviceCategoryRepository = serviceCategoryRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    public Map<String, List<Rentable>> getAllRentables(int userId) {
        Map params = new HashMap();
        List<ItemCategory> itemCategoryList = itemCategoryRepository.findAll();
        List<ServiceCategory> serviceCategoryList = serviceCategoryRepository.findAll();
        List<Rentable> rentableList = new ArrayList<>();
        rentableList.addAll(itemRepository.findAll());
        rentableList.addAll(serviceRepository.findAll());
        params.put("rentableList", rentableList);
        params.put("itemCategories", itemCategoryList);
        params.put("serviceCategories", serviceCategoryList);
        params.put("userId", userId);
        return params;
    }

    public List<? extends Rentable> getUpdatedData(String id) {
        List<? extends Rentable> resultList = new ArrayList<>();
        String[] idParts = id.replace("\"", "").split("_");
        String type = idParts[0];
        long idNumber = Long.parseLong(idParts[1]);
        switch (type) {
            case "item":
                resultList = itemRepository.findByItemCategoryId(idNumber);
                break;
            case "allitems":
                resultList = itemRepository.findAll();
                break;
            case "service":
                resultList = serviceRepository.findByServiceCategoryId(idNumber);
                break;
            case "allservices":
                resultList = serviceRepository.findAll();
                break;
            case "status":
                resultList = getRented(idNumber);
                break;
        }
        return resultList;
    }

    public void add(Map<String, String> requestParams, String userId){
        String name = requestParams.get("name");
        String description = requestParams.get("description");
        String amount = requestParams.get("price");
        String type = requestParams.get("type");
        String categoryId = requestParams.get("category");
        Price price = new Price();
        price.setAmount(Integer.parseInt(amount));
        price.setCurrency(Currency.getInstance("EUR"));

        if (type.equals("item")){
            Item newItem = new Item();
            newItem.setName(name);
            newItem.setDescription(description);
            newItem.setPrice(price);
            newItem.setItemCategory(itemCategoryRepository.findOne(Long.parseLong(categoryId)));
            newItem.setUser(userRepository.findOne(Long.parseLong(userId)));
            newItem.setStatus(Status.AVAILABLE);
            itemRepository.save(newItem);

        } else if (type.equals("service")){
            Service newService = new Service();
            newService.setName(name);
            newService.setDescription(description);
            newService.setPrice(price);
            newService.setServiceCategory(serviceCategoryRepository.findOne(Long.parseLong(categoryId)));
            newService.setUser(userRepository.findOne(Long.parseLong(userId)));
            newService.setStatus(Status.AVAILABLE);
            serviceRepository.save(newService);


        }
    }

    public List<Rentable> getRented(long status) {
        List<Rentable> resultList = new ArrayList<>();
        List<Item> itemList = new ArrayList<>();
        List<Service> serviceList = new ArrayList<>();
        switch (Math.toIntExact(status)) {
            case 1:
                itemList = itemRepository.findByStatus(Status.AVAILABLE);
                serviceList = serviceRepository.findByStatus(Status.AVAILABLE);
                break;
            case 2:
                itemList = itemRepository.findByStatus(Status.RENTED);
                serviceList = serviceRepository.findByStatus(Status.RENTED);
                break;
            case 3:
                itemList = itemRepository.findByStatus(Status.DAMAGED);
                serviceList = serviceRepository.findByStatus(Status.DAMAGED);
                break;
        }
        resultList.addAll(itemList);
        resultList.addAll(serviceList);
        return resultList;
    }

    public Rentable getRentableById(String id) {
        Rentable result = itemRepository.findOne(Long.parseLong(id));
        if (result == null){
            result = serviceRepository.findOne(Long.parseLong(id));
        }
        return result;
    }

    public void resetRentablesToActive(List<Long> outdated) {
        System.out.println("itt e");
        System.out.println("size: "+ outdated.size());
        for (int i = 0; i < outdated.size(); i++) {
            System.out.println("itt is e");
            itemRepository.refreshStatusById(outdated.get(i), Status.AVAILABLE);
            serviceRepository.refreshStatusById(outdated.get(i), Status.AVAILABLE);
        }
    }
}
