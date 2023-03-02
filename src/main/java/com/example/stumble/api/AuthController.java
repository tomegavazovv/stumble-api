package com.example.stumble.api;

import com.example.stumble.entities.User;
import com.example.stumble.enums.Gender;
import com.example.stumble.exceptions.UserAlreadyExists;
import com.example.stumble.services.interfaces.AuthService;
import com.example.stumble.utils.StorageUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam MultipartFile imagePath,
            @RequestParam Gender gender,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String instagramAccount,
            @RequestParam(required = false) String facebookAccount,
            @RequestParam(required = false) String linkedinAccount) {

        User user = new User.Builder()
                .firstName(firstName)
                .lastName(lastName)
                .gender(gender)
                .email(email)
                .password(password)
                .description(description)
                .instagramAccount(instagramAccount)
                .facebookAccount(facebookAccount)
                .linkedinAccount(linkedinAccount)
                .lat(10.0)
                .lon(10.0)
                .build();
        try {
            StorageUtils.uploadImage(imagePath, user.getEmail());
            user.setImagePath(StorageUtils.getPath(imagePath, user.getEmail()));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Image could not be uploaded.");
        }
        authService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<?> exceptionHandler() {
        return ResponseEntity.status(409).body("Error: User with that email already exists");
    }
}
