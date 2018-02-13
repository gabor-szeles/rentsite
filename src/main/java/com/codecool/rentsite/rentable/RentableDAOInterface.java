package com.codecool.rentsite.rentable;

import com.codecool.rentsite.rentable.Item;

import java.util.List;

public interface RentableDAOInterface {
    Rentable find(int id);
    List<Rentable> getAll();
    List<Rentable> getByItemCategory(int id);
    List<Rentable> getByServiceCategory(int id);
}
