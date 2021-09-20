package com.example.userservice.Controller;

import com.example.userservice.Dto.ValidateEntity;
import com.example.userservice.Services.Registration.VerificationCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/validate")
public class ValidateController{

    private VerificationCodeService verificationCodeService;

    @Autowired
    public ValidateController(VerificationCodeService verificationCodeService) {
        this.verificationCodeService = verificationCodeService;
    }

    @PostMapping
    public ResponseEntity<Void> validateCodeForUser(@RequestBody @Valid ValidateEntity validateEntity, Errors errors){
        try{
            verificationCodeService.validateVerificationCode(validateEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (AuthenticationException e){
            log.error("Verification Code provided has failed "+ e);
            log.error(errors.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
