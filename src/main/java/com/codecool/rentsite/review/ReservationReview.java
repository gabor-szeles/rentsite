package com.codecool.rentsite.review;

import com.codecool.rentsite.reservation.Reservation;

import javax.persistence.*;

@Entity
public class ReservationReview extends Review {

    @OneToOne
    private Reservation reservation;

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
