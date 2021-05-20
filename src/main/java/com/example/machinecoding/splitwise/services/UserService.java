package com.example.machinecoding.splitwise.services;

import com.example.machinecoding.splitwise.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    private Map<String, User> userMap;

    public UserService() {
        this.userMap = new HashMap<>();
    }

    public void addUser(User user) {
        userMap.put(user.getUserId(), user);
    }

    public User findUserById(String id) {
        return userMap.get(id);
    }

}
