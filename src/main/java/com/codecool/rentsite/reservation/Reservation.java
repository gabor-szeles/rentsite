package com.codecool.rentsite.reservation;

import java.util.Date;



public class Reservation implements Reviewable {

    private Integer id;
    private Integer userId;
    private Integer ratableId;
    private Date rentFrom;
    private Date rentTo;

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
        return ratableId;
    }

    public void setRatableId(Integer ratableId) {
        this.ratableId = ratableId;
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
