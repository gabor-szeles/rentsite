package com.codecool.rentsite.rentable.category;

import com.codecool.rentsite.rentable.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class CategoryDAO implements CategoryDAOInterface {

    private EntityManager entityManager;

    public CategoryDAO(EntityManagerFactory emf) {
        this.entityManager = emf.createEntityManager();
    }

    @Override
    public List<ItemCategory> getItemCategories() {
        TypedQuery<ItemCategory> itemCategoryQuery = entityManager.createNamedQuery("itemCategory.all", ItemCategory.class);
        List<ItemCategory> itemCategoryList = itemCategoryQuery.getResultList();
        return itemCategoryList;
    }

    @Override
    public List<ServiceCategory> getServiceCategories() {
        TypedQuery<ServiceCategory> serviceCategoryQuery = entityManager.createNamedQuery("serviceCategory.all", ServiceCategory.class);
        List<ServiceCategory> serviceCategoryList = serviceCategoryQuery.getResultList();
        return serviceCategoryList;
    }
}
