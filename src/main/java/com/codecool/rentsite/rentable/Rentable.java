package com.codecool.rentsite.rentable;

import com.codecool.rentsite.reservation.Reservation;
import com.codecool.rentsite.user.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "seq", initialValue = 5, allocationSize = 100)
public abstract class Rentable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    private User user;

    @ManyToMany(mappedBy = "rentableSet")
    private Set<Tag> tagSet = new HashSet<>();
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "price")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency"))
    })
    private Price price;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "rentable")
    private Set<Reservation> reservationSet = new HashSet<>();

    Rentable(String name, User user, Price price) {
        this.name = name;
        this.user = user;
        this.price = price;
        this.status = Status.AVAILABLE;
    }

    public Rentable() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
