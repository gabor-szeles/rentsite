package com.codecool.rentsite.user;

public interface UserDAOInterface {
    void add(String username, String password, String email);
    User find(int id);
}
