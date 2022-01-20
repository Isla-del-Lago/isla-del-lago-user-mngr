package com.isladellago.usermanager.service;

import com.isladellago.usermanager.domain.model.User;

import java.util.List;

public interface UserService {

    /**
     * Creates and saves a new user in database
     *
     * @param user User to be created.
     * @return User created id.
     */
    Integer createUser(User user);

    /**
     * Get an user registered on the dataabse by
     * the given email.
     *
     * @param email Email to search the user.
     * @return The founded user.
     */
    User getUserByEmail(String email);

    /**
     * Get all registered users on database.
     *
     * @return List with all fetched users.
     */
    List<User> getAllUsers();

    /**
     * Deletes a user from the database by
     * the given email.
     *
     * @param userEmail User email to be deleted.
     */
    void deleteUserByEmail(String userEmail);
}
