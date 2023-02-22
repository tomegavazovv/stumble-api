package com.example.stumble.api;

import com.example.stumble.DTO.NearbyUserDTO;
import com.example.stumble.DTO.UserDetailsDTO;
import com.example.stumble.converters.UserToNearbyUser;
import com.example.stumble.converters.UserToUserDetails;
import com.example.stumble.entities.User;
import com.example.stumble.exceptions.UserNotFoundException;
import com.example.stumble.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserToNearbyUser nearbyUserConverter;
    private final UserToUserDetails userDetailsConverter;
    private final UserService userService;

    public UserController(UserService userService, UserToNearbyUser nearbyUserConverter, UserToUserDetails userDetailsConverter){
        this.userService = userService;
        this.nearbyUserConverter = nearbyUserConverter;
        this.userDetailsConverter = userDetailsConverter;
    }

    @GetMapping
    public String home(){
        userService.k();
        return "gotovo";
    }

    @GetMapping("/nearby/{id}/{lat}/{lon}")
    public ResponseEntity<List<NearbyUserDTO>> getNearbyUsers(
            @PathVariable Long id, @PathVariable Double lat, @PathVariable Double lon) {

        List<NearbyUserDTO> users = userService.getNearbyUsers(id, lat, lon).stream()
                .map(nearbyUserConverter::convert).collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }

    @PostMapping("/block")
    public void blockUser(Long userId, Long blockUserId){
        userService.blockUser(userId, blockUserId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDTO> getDetails(@PathVariable Long id){
        try{
            User user = userService.getUserDetails(id);
            return new ResponseEntity<>(userDetailsConverter.convert(user), HttpStatus.OK);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

