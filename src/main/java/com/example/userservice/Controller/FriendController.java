package com.example.userservice.Controller;

import com.example.userservice.Dto.User;
import com.example.userservice.Dto.UserViews;
import com.example.userservice.Repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/friend")
@Slf4j
public class FriendController {

    final UserRepository userRepository;

    @Autowired
    public FriendController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/allusers")
    public ResponseEntity<?> getAllUsers(@AuthenticationPrincipal Principal principal) throws JsonProcessingException {
        List<User> users = userRepository.findAll();

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String allUsers = mapper.writerWithView(UserViews.NoFriendView.class).writeValueAsString(users.get(0));

        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

}
