package com.schedule.domain.repository;

import com.schedule.domain.model.User;

import java.util.List;

public interface UserRepository {

    User getUser(String name);

    void addUser(String name, String password, String address);

    List<User> searchUsers(String name);

    void editUser(String name, String password, String address);

    void deleteUser(String name);
}
