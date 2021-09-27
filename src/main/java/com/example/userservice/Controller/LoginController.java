package com.example.userservice.Controller;

import com.example.userservice.Dto.RequestDto;
import com.example.userservice.Dto.ResponseDto;
import com.example.userservice.Security.MyUserDetailsService;
import com.example.userservice.Util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@Valid @RequestBody RequestDto requestDto, Errors errors){

        if(errors.hasErrors()){
            log.error(errors.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getEmail(),requestDto.getPassword()));
        } catch (AuthenticationCredentialsNotFoundException e){
            log.error("Incorrect username and password has been provided");
            log.error(e.getMessage());
            throw new BadCredentialsException("Bad Username and password provided", e);
        }

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(requestDto.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails);

        return new ResponseEntity<>(new ResponseDto(jwt),HttpStatus.ACCEPTED);
    }
}
