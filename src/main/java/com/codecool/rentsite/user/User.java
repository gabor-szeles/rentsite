package com.codecool.rentsite.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    int id;

    String name;

    public User(String name) {
        this.name = name;
    }
}
