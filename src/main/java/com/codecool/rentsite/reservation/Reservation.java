package com.codecool.rentsite.reservation;

import java.util.Date;



public class Reservation implements Reviewable {

    private Integer id;
    private Integer userId;
    private Integer rentableId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRatableId() {
        return rentableId;
    }

    public void setRatableId(Integer ratableId) {
        this.rentableId = ratableId;
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
}
