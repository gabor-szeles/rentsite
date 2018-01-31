package com.codecool.rentsite.reservation;

import com.codecool.rentsite.rentable.Rentable;
import com.codecool.rentsite.review.Reviewable;
import com.codecool.rentsite.user.User;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Reservation implements Reviewable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Rentable rentable;
    private Date rentFrom;
    private Date rentTo;

    public Reservation(Date rentFrom, Date rentTo) {
        this.rentFrom = rentFrom;
        this.rentTo = rentTo;
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
}
