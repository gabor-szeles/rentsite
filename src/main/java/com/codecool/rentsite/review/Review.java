package com.codecool.rentsite.review;

import com.codecool.rentsite.reservation.Reservation;
import com.codecool.rentsite.user.User;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.MetaValue;

import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String description;

    @ManyToOne
    private User author;

    @Any(metaColumn = @Column(name = "review_type"))
    @AnyMetaDef(idType = "int", metaType = "string",
            metaValues = {
                    @MetaValue(targetEntity = User.class, value = "user"),
                    @MetaValue(targetEntity = Reservation.class, value = "reservation")
            })
    @Cascade( { org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "target_Id")
    private Reviewable review;

    private int rate;
    public Review(String description, User author, Reviewable reviewable) {
        this.description = description;
        this.author = author;
        this.review = reviewable;
        this.rate = 0;
    }


    public int getId() {
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

    public void setId(int id) {
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

    public Reviewable getReview() {
        return review;
    }

    public void setReview(Reviewable review) {
        this.review = review;
    }
}
