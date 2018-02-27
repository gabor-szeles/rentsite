package com.codecool.rentsite.user;

import com.codecool.rentsite.rentable.Rentable;
import com.codecool.rentsite.reservation.Reservation;
import com.codecool.rentsite.review.Review;
import com.codecool.rentsite.review.UserReview;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@SequenceGenerator(name = "seq", initialValue = 5, allocationSize = 100)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;
    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<Reservation> reservationSet = new HashSet<>();

    public Set<Reservation> getReservationSet() {
        return reservationSet;
    }

    @OneToMany(mappedBy = "user")
    private Set<Rentable> rentableSet = new HashSet<>();

    public Set<Rentable> getRentableSet() {
        return rentableSet;
    }

    @OneToMany(mappedBy = "author")
    private Set<Review> writtenReviews = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserReview> recievedReviews = new HashSet<>();

    public User(){
    }

    public User(String username) {
        this.username = username;
    }

    public User(String firstName, String lastName, String username, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    //Getters
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }


    //Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserReview> getRecievedReviews() {
        return recievedReviews;
    }

    public void setRecievedReviews(Set<UserReview> recievedReviews) {
        this.recievedReviews = recievedReviews;
    }
}
