package com.example.stumble.dtos;

import com.example.stumble.enums.Gender;
import lombok.Data;

@Data
public class UserDetailsDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String description;
    private Gender gender;
    private String instagramAccount;
    private String facebookAccount;
    private String linkedinAccount;
    private String imagePath;
}
