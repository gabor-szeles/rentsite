package com.codecool.rentsite.DAO;

import com.codecool.rentsite.rentable.Item;
import com.codecool.rentsite.rentable.Rentable;
import com.codecool.rentsite.rentable.Service;
import com.codecool.rentsite.user.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class RentableDAO {


    private EntityManager entityManager;

    public RentableDAO(EntityManagerFactory emf){
        this.entityManager = emf.createEntityManager();
    }

    public List<Item> executeItemQuery(){
        TypedQuery<Item> itemQuery = entityManager.createNamedQuery("item.all", Item.class);
        List<Item> items = itemQuery.getResultList();
        return items;
    }

    public List<Service> executeServiceQuery(){
        TypedQuery<Service> serviceTypedQuery = entityManager.createNamedQuery("service.all", Service.class);
        List<Service> services = serviceTypedQuery.getResultList();
        return services;
    }
}
