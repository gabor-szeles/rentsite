package com.codecool.rentsite.rentable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentableService {

    private final RentableDAO rentableDAO;

    public RentableService(RentableDAO rentableDAO) {
        this.rentableDAO = rentableDAO;
    }

    public Map<String, List<Rentable>> getAllRentables() {
        Map params = new HashMap();
        List<Rentable> rentableList = rentableDAO.getAll();
        params.put("rentableList", rentableList);
        return params;
    }
}
