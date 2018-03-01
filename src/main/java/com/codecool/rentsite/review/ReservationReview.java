package com.codecool.rentsite.review;

import com.codecool.rentsite.reservation.Reservation;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
public class ReservationReview extends Review {

    @OneToOne
    private Reservation reservation;

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
