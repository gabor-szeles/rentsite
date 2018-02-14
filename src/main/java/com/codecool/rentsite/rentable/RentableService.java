package com.codecool.rentsite.rentable;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentableService {

    private final EntityManagerFactory ENTITY_MANAGER_FACTORY;

    public RentableService(EntityManagerFactory ENTITY_MANAGER_FACTORY) {
        this.ENTITY_MANAGER_FACTORY = ENTITY_MANAGER_FACTORY;
    }

    public Map<String, List<Rentable>> getAllRentables() {
        Map params = new HashMap();
        RentableDAO rentableDAO = new RentableDAO(ENTITY_MANAGER_FACTORY);
        List<Rentable> rentableList = rentableDAO.getAll();
        params.put("rentableList", rentableList);
        return params;
    }
}
