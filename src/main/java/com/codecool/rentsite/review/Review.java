package com.codecool.rentsite.review;

import com.codecool.rentsite.user.User;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String description;

    @ManyToOne
    private User author;

    private int rate;

    public Review(String description, User author) {
        this.description = description;
        this.author = author;
        this.rate = 0;
    }

    public Review() {
    }


    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public User getAuthor() {
        return author;
    }


    public int getRate() {
        return rate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
