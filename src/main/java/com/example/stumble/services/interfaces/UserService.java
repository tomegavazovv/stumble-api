package com.example.stumble.services.interfaces;

import com.example.stumble.dtos.UserDetailsDTO;
import com.example.stumble.entities.User;
import com.example.stumble.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    List<User> findNearbyUsers(Long id, Double lat, Double lon);
    UserDetailsDTO blockUser(Long userId, Long blockUserId) throws UserNotFoundException;
    User findUserDetails(String email) throws UserNotFoundException;
    Long findUserIdByEmail(String email);

}
