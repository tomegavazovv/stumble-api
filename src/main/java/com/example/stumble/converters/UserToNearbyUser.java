package com.example.stumble.converters;

import com.example.stumble.DTO.NearbyUserDTO;
import com.example.stumble.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;


@Component
public class UserToNearbyUser implements Converter<User, NearbyUserDTO> {
    @Override
    public NearbyUserDTO convert(User user) {
        NearbyUserDTO userDTO = new NearbyUserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setId(user.getID());
        userDTO.setLastName(user.getLastName());
        userDTO.setLat(user.getLat());
        userDTO.setLon(user.getLon());
        userDTO.setImage(user.getImage());
        return userDTO;
    }

}
