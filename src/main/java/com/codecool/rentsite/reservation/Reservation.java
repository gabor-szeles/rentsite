package com.codecool.rentsite.reservation;

import com.codecool.rentsite.rentable.Rentable;
import com.codecool.rentsite.review.ReservationReview;
import com.codecool.rentsite.review.Review;
import com.codecool.rentsite.user.User;

import javax.persistence.*;
import java.util.Date;


@Entity
@NamedQueries(@NamedQuery(name = "reservation.all", query = "SELECT s FROM Reservation s "))
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Rentable rentable;
    private Date rentFrom;
    private Date rentTo;

    @OneToOne(mappedBy = "reservation")
    private ReservationReview reservationReview;

    public Reservation(User user, Date rentFrom, Date rentTo) {
        this.rentFrom = rentFrom;
        this.rentTo = rentTo;
    }

    public Reservation() {
    }

    public String getUserName(){
        return this.user.getUsername();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Date getRentFrom() {
        return rentFrom;
    }

    public void setRentFrom(Date rentFrom) {
        this.rentFrom = rentFrom;
    }

    public Date getRentTo() {
        return rentTo;
    }

    public void setRentTo(Date rentTo) {
        this.rentTo = rentTo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Rentable getRentable() {
        return rentable;
    }

    public void setRentable(Rentable rentable) {
        this.rentable = rentable;
    }

    public ReservationReview getReservationReview() {
        return reservationReview;
    }

    public void setReservationReview(ReservationReview reservationReview) {
        this.reservationReview = reservationReview;
    }
}
