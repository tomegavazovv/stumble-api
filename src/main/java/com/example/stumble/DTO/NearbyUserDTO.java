package com.example.stumble.DTO;

import lombok.Data;

@Data
public class NearbyUserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String image;
    private Double lat;
    private Double lon;
}
