package com.example.stumble.api;

import com.example.stumble.DTO.BlockUserBody;
import com.example.stumble.DTO.NearbyUserDTO;
import com.example.stumble.DTO.UserDetailsDTO;
import com.example.stumble.converters.NearbyUserConverter;
import com.example.stumble.converters.UserDetailsConverter;
import com.example.stumble.entities.User;
import com.example.stumble.exceptions.UserNotFoundException;
import com.example.stumble.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final NearbyUserConverter nearbyUserConverter;
    private final UserDetailsConverter userDetailsConverter;
    private final UserService userService;

    public UserController(UserService userService, NearbyUserConverter nearbyUserConverter, UserDetailsConverter userDetailsConverter){
        this.userService = userService;
        this.nearbyUserConverter = nearbyUserConverter;
        this.userDetailsConverter = userDetailsConverter;
    }

    @GetMapping("/nearby/{id}/{lat}/{lon}")
    public ResponseEntity<List<NearbyUserDTO>> findNearbyUsers(
            @PathVariable Long id, @PathVariable Double lat, @PathVariable Double lon) {
        List<NearbyUserDTO> users = userService.findNearbyUsers(id, lat, lon).stream()
                .map(nearbyUserConverter::convert).collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/block")
    public ResponseEntity<UserDetailsDTO> blockUser(@RequestBody BlockUserBody body){
        return new ResponseEntity<>(userService.blockUser(body.getUserId(), body.getBlockUserId()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDTO> findDetails(@PathVariable Long id){
        try{
            User user = userService.findUserDetails(id);
            return new ResponseEntity<>(userDetailsConverter.convert(user), HttpStatus.OK);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

