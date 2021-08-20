package com.example.userservice.Controller;

import com.example.userservice.Dto.User;
import com.example.userservice.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/register")
@Slf4j
public class RegistrationController {

    private UserRepository userRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<User> Register(@Valid @RequestBody User user, Errors errors){
        log.info("The Register Controller has been called");
        if(user == null || errors.hasErrors()){
            log.warn("User is empty, bad request ");
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
        user.setUserStatus(User.Status.PENDING);
        userRepository.save(user);
        log.info("User has been successfully written to the database");
        return new ResponseEntity<User>(HttpStatus.OK);

    }
}
