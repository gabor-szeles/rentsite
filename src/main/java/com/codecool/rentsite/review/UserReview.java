package com.codecool.rentsite.review;

import com.codecool.rentsite.user.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@Entity
public class UserReview extends Review {

    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
