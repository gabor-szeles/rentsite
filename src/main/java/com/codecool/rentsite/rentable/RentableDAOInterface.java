package com.codecool.rentsite.rentable;

import com.codecool.rentsite.rentable.Item;
import com.codecool.rentsite.reservation.Reservation;

import java.util.List;

public interface RentableDAOInterface {
    Rentable find(int id);
    List<Rentable> getAll();
    List<Item> getAllItems();
    List<Service> getAllServices();
    List<Rentable> getByItemCategory(int id);
    List<Rentable> getByServiceCategory(int id);
    List<Reservation> getRented(String status);
}
