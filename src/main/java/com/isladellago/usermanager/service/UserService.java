package com.isladellago.usermanager.service;

import com.isladellago.usermanager.model.User;

import java.util.List;

public interface UserService {

    Integer createUser(User user);

    User getUserByEmail(String email);

    List<User> getAllUsers();
}
