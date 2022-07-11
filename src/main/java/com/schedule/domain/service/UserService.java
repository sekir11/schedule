package com.schedule.domain.service;

import com.schedule.domain.model.User;

import java.util.List;

public interface UserService {

    User login(String name, String password);

    User signUp(String name, String password, String address);

    List<User> searchUsers(String name);

    void editUser(String oldName, String newName, String password, String address);
}
