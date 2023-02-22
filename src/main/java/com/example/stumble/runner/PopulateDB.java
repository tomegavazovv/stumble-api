package com.example.stumble.runner;

import com.example.stumble.entities.User;
import com.example.stumble.enums.Gender;
import com.example.stumble.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PopulateDB implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.delete(userRepository.findById(1L).get());


    }
}
