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

    private static RentableDAO rentableDAOInstance = null;

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaexamplePU");
    private final EntityManager em = emf.createEntityManager();

    private RentableDAO(){}

    public static RentableDAO getInstance() {
        if(rentableDAOInstance == null) {
            rentableDAOInstance = new RentableDAO();
        }
        return rentableDAOInstance;
    }

    public List<Item> executeItemQuery(){
        TypedQuery<Item> itemQuery = em.createNamedQuery("item.all", Item.class);
        List<Item> items = itemQuery.getResultList();
        return items;
    }

    public List<Service> executeServiceQuery(){
        TypedQuery<Service> serviceTypedQuery = em.createNamedQuery("service.all", Service.class);
        List<Service> services = serviceTypedQuery.getResultList();
        return services;
    }
}
