package com.example.userservice.Controller;

import com.example.userservice.Dto.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class SecurityController {

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest details) throws Exception{
        return null;
    }
}
