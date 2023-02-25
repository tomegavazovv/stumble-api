package com.example.stumble.dtos;

import lombok.Data;

@Data
public class NearbyUserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String imagePath;
    private Double lat;
    private Double lon;
}
