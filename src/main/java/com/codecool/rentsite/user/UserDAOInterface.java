package com.codecool.rentsite.user;

public interface UserDAOInterface {
    void add(String username, String password, String email, String firstName, String lastName);
    User find(String userName);
}
