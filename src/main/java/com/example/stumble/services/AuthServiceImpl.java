package com.example.stumble.services;

import com.example.stumble.entities.User;
import com.example.stumble.exceptions.UserAlreadyExists;
import com.example.stumble.repositories.UserRepository;
import com.example.stumble.services.interfaces.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(User user) {
        Optional<User> checkUser = userRepository.findUserByEmail(user.getEmail());
        if(checkUser.isPresent()){
            throw new UserAlreadyExists();
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }


}
