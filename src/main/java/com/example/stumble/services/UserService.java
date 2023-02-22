package com.example.stumble.services;

import com.example.stumble.entities.User;
import com.example.stumble.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    List<User> findNearbyUsers(Long id, Double lat, Double lon);
    void blockUser(Long userId, Long blockUserId) throws UserNotFoundException;
    User findUserDetails(Long id) throws UserNotFoundException;
}
