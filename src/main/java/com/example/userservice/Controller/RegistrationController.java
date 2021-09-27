package com.example.userservice.Controller;

import com.example.userservice.Dto.ResponseDto;
import com.example.userservice.Dto.User;
import com.example.userservice.Dto.VerificationCode;
import com.example.userservice.Services.Registration.RegisterService;
import com.example.userservice.Services.Registration.VerificationCodeService;
import com.example.userservice.Util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
@Slf4j
public class RegistrationController {


    private final RegisterService registerService;
    private final VerificationCodeService verificationCodeService;
    private JwtUtil jwtUtil;

    @Autowired
    public RegistrationController(RegisterService registerService, VerificationCodeService verificationCodeService, JwtUtil jwtUtil) {
        this.registerService = registerService;
        this.verificationCodeService = verificationCodeService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody User user, Errors errors){
        log.info("The Register Controller has been called");
        if(user == null || errors.hasErrors()){
            log.warn("User is empty or User Data is not sufficient");
            log.warn(errors.toString());
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }

        registerService.registerNewUser(user);
        log.info("User has been successfully written to the database");
        String token = jwtUtil.generateToken(user);
        return new ResponseEntity<>(new ResponseDto(token),HttpStatus.OK);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(@RequestParam String email){
        return new ResponseEntity<>("yoooo   " +email,HttpStatus.OK);
    }
}
