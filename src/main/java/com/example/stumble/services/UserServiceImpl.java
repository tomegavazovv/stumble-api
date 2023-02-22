package com.example.stumble.services;

import com.example.stumble.entities.User;
import com.example.stumble.exceptions.UserNotFoundException;
import com.example.stumble.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getNearbyUsers(Long id, Double lat, Double lon) {
        return userRepository.findNearbyUsers(id, lat, lon);
    }

    @Override
    public void blockUser(Long userId, Long blockUserId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        User blockUser = userRepository.findById(blockUserId).orElseThrow(UserNotFoundException::new);
        user.getBlockedUsers().add(blockUser);
        userRepository.save(user);
    }

    @Override
    public User getUserDetails(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void k(){
        User user = userRepository.findById(1L).get();
        user.setImage("image1.jpeg");
        userRepository.save(user);
    }
}
