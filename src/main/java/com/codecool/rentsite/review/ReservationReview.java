package com.codecool.rentsite.review;

import com.codecool.rentsite.reservation.Reservation;

import javax.persistence.*;

@Entity
@Table(name="reservationreview")
public class ReservationReview extends Review {

    @OneToOne
    private Reservation reservation;

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
