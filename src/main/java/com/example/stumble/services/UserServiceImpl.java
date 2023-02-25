package com.example.stumble.services;

import com.example.stumble.DTO.UserDetailsDTO;
import com.example.stumble.converters.UserDetailsConverter;
import com.example.stumble.entities.User;
import com.example.stumble.exceptions.UserNotFoundException;
import com.example.stumble.repositories.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findNearbyUsers(Long id, Double lat, Double lon) {
        return userRepository.findNearbyUsers(id, lat, lon);
    }

    @Override
    public UserDetailsDTO blockUser(Long userId, Long blockUserId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        User blockUser = userRepository.findById(blockUserId).orElseThrow(UserNotFoundException::new);
        user.getBlockedUsers().add(blockUser);
        userRepository.save(user);
        return new UserDetailsConverter().convert(blockUser);
    }

    @Override
    public User findUserDetails(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Long findUserIdByEmail(String email) {
        return userRepository.findUserIdByEmail(email);
    }


}
