package com.ecnu.service;

import com.ecnu.model.User;

import java.util.HashMap;

import java.util.List;

/**
 * Created by kun on 17-6-26.
 */
public interface UserService {

    boolean addUser(User user);
    User getUserbyName(String nickname);
    int register(User user);
    int login(User user);
}
