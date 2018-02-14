package com.codecool.rentsite.rentable;

import com.codecool.rentsite.rentable.Item;
import com.codecool.rentsite.reservation.Reservation;

import java.util.List;

public interface RentableDAOInterface {
    Rentable find(int id);
    List<Rentable> getAll();
    List<Item> getAllItems();
    List<Service> getAllServices();
    List<Item> getByItemCategory(int id);
    List<Service> getByServiceCategory(int id);
    List<Rentable> getRented(int status);
}
