package com.example.stumble.entities;

import com.example.stumble.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;
    @NotNull

    private String lastName;

    private String email;

    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;

    private String instagramAccount;

    private String facebookAccount;

    private String linkedinAccount;

    private String image;

    private Double lat;

    private Double lon;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="blocked_users",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns={@JoinColumn(name="blocked_user_id")})
    private Set<User> blockedUsers = new HashSet<>();
}
