package com.example.stumble.entities;

import com.example.stumble.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Email;
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

    @NotNull
    private String image;

    @NotNull
    private Double lat;

    @NotNull
    private Double lon;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;

    @Email
    private String email;

    private String description;

    private String instagramAccount;

    private String facebookAccount;

    private String linkedinAccount;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="blocked_users",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns={@JoinColumn(name="blocked_user_id")})
    private Set<User> blockedUsers = new HashSet<>();
}
