package com.codecool.rentsite.reservation;

import java.util.List;

public interface ReservationDAOInterface {
    Reservation find(int id);
    Reservation add();
    List<Reservation> getAll();
}
