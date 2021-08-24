package com.example.userservice.Controller;

import com.example.userservice.Dto.User;
import com.example.userservice.Dto.VerificationCode;
import com.example.userservice.Services.Registration.RegisterService;
import com.example.userservice.Services.Registration.VerificationCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
@Slf4j
public class RegistrationController {


    private RegisterService registerService;
    private VerificationCodeService verificationCodeService;

    @Autowired
    public RegistrationController(RegisterService registerService, VerificationCodeService verificationCodeService) {
        this.registerService = registerService;
        this.verificationCodeService = verificationCodeService;
    }

    @PostMapping
    public ResponseEntity<User> register(@Valid @RequestBody User user, Errors errors){
        log.info("The Register Controller has been called");
        if(user == null || errors.hasErrors()){
            log.warn("User is empty or User Data is not sufficient");
            log.warn(errors.toString());
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }

        registerService.registerNewUser(user);
        log.info("User has been successfully written to the database");
        return new ResponseEntity<User>(HttpStatus.OK);

    }

    @PostMapping("/resend")
    public ResponseEntity<String> resend(@RequestBody String email){
        try{
            log.info("Resetting the verification code for "+ email);
            verificationCodeService.resendVerification(email);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            log.info("There has been an issue, resetting the verification code for the email "+email);
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }
}
