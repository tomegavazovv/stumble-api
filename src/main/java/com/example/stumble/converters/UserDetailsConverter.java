package com.example.stumble.converters;

import com.example.stumble.dtos.UserDetailsDTO;
import com.example.stumble.entities.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsConverter implements Converter<User, UserDetailsDTO> {
    @Override
    public UserDetailsDTO convert(User user) {
        UserDetailsDTO dto = new UserDetailsDTO();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setGender(user.getGender());
        dto.setEmail(user.getEmail());
        dto.setDescription(user.getDescription());
        dto.setFacebookAccount(user.getFacebookAccount());
        dto.setInstagramAccount(user.getInstagramAccount());
        dto.setLinkedinAccount(user.getLinkedinAccount());
        dto.setImagePath(user.getImagePath());
        return dto;
    }
}
