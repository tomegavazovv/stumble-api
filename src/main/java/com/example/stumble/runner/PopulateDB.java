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
        User user = new User();
        user.setId(1L);
        user.setFirstName("Tome");
        user.setLastName("Gavazov");
        user.setEmail("tomegavazov@gmail.com");
        user.setFacebookAccount("facebook_acc");
        user.setInstagramAccount("instagram_acc");
        user.setLinkedinAccount("linkedin_acc");
        user.setDescription("description");
        user.setImage("/photo1.jpeg");
        user.setGender(Gender.MALE);
        user.setLat(10.0);
        user.setLon(10.0);

        userRepository.save(user);
    }
}
