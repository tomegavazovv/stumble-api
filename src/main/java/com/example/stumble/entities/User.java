package com.example.stumble.entities;

import com.example.stumble.enums.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
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
    private String imagePath;

    private Double lat;

    private Double lon;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;

    @Email
    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    private String description;

    private String instagramAccount;

    private String facebookAccount;

    private String linkedinAccount;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="blocked_users",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns={@JoinColumn(name="blocked_user_id")})
    private Set<User> blockedUsers = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="user_messages",
            joinColumns={@JoinColumn(name="sender_id")},
            inverseJoinColumns={@JoinColumn(name="receiver_id")})
    private Set<User> userMessages = new HashSet<>();

    public User(Builder builder){
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.gender = builder.gender;
        this.email = builder.email;
        this.password = builder.password;
        this.description = builder.description;
        this.instagramAccount = builder.instagramAccount;
        this.facebookAccount = builder.facebookAccount;
        this.linkedinAccount = builder.linkedinAccount;
        this.lat = builder.lat;
        this.lon = builder.lon;
    }

    public static class Builder{
        private String firstName;
        private String lastName;
        private Double lat;
        private Double lon;
        private Gender gender;
        private String email;
        private String password;
        private String description;
        private String instagramAccount;
        private String facebookAccount;
        private String linkedinAccount;

        public Builder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder gender(Gender gender){
            this.gender = gender;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }

        public Builder instagramAccount(String instagramAccount){
            this.instagramAccount = instagramAccount;
            return this;
        }

        public Builder facebookAccount(String facebookAccount){
            this.facebookAccount = facebookAccount;
            return this;
        }

        public Builder linkedinAccount(String linkedinAccount){
            this.linkedinAccount = linkedinAccount;
            return this;
        }

        public Builder lat(Double lat){
            this.lat = lat;
            return this;
        }

        public Builder lon(Double lon){
            this.lon = lon;
            return this;
        }

        public User build(){
            return new User(this);
        }


    }
}
