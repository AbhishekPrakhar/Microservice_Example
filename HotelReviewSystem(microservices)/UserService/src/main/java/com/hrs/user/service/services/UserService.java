package com.hrs.user.service.services;

import com.hrs.user.service.entites.User;

import java.util.List;

public interface UserService {
    // user operations

    //create
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //get single user with given id
    User getUser(String userId);

    //TODO: delete, update
}
