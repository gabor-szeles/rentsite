package com.codecool.rentsite.review;

import com.codecool.rentsite.user.User;

public class Review {
    private int id;
    private String description;
    private User author;
    private Reviewable reviewable;
    private int targetId;
    private int rate;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public User getAuthor() {
        return author;
    }

    public Reviewable getReviewable() {
        return reviewable;
    }

    public int getTargetId() {
        return targetId;
    }

    public int getRate() {
        return rate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setReviewable(Reviewable reviewable) {
        this.reviewable = reviewable;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
