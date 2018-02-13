package com.codecool.rentsite.rentable;

import com.codecool.rentsite.rentable.Item;
import com.codecool.rentsite.rentable.Rentable;
import com.codecool.rentsite.rentable.Service;
import com.codecool.rentsite.user.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class RentableDAO implements RentableDAOInterface {


    private EntityManager entityManager;

    public RentableDAO(EntityManagerFactory emf){
        this.entityManager = emf.createEntityManager();
    }


    @Override
    public Rentable find(int id) {
        return null;
    }

    @Override
    public List<Rentable> getAll() {
        List<Rentable> resultList = new ArrayList<>();
        TypedQuery<Item> itemQuery = entityManager.createNamedQuery("item.all", Item.class);
        List<Item> items = itemQuery.getResultList();
        resultList.addAll(items);
        TypedQuery<Service> serviceTypedQuery = entityManager.createNamedQuery("service.all", Service.class);
        List<Service> services = serviceTypedQuery.getResultList();
        resultList.addAll(services);
        return resultList;
    }

    @Override
    public List<Rentable> getByItemCategory(int id) {
        return null;
    }

    @Override
    public List<Rentable> getByServiceCategory(int id) {
        return null;
    }
}
