package com.example.userservice.Services.Registration;

import com.example.userservice.Dto.User;
import com.example.userservice.Dto.ValidateEntity;
import com.example.userservice.Dto.VerificationCode;
import com.example.userservice.Repository.UserRepository;
import com.example.userservice.Repository.VerificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.security.auth.message.AuthException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class VerificationCodeService {

    private final UserRepository userRepository;
    private final  VerificationRepository verificationRepository;

    @Autowired
    public VerificationCodeService(UserRepository userRepository, VerificationRepository verificationRepository) {
        this.userRepository = userRepository;
        this.verificationRepository = verificationRepository;
    }

    public void resendVerification(String email){
        VerificationCode oldVerificationCode = getVerificationCodeByEmail(email);
        log.info("Old verification code has been found for that user");
        oldVerificationCode.setCreationTimeStamp(new Timestamp(System.currentTimeMillis()).toInstant());
        oldVerificationCode.setVerificationCode(generateRandomCode());

        verificationRepository.save(oldVerificationCode);
    }

    public void validateVerificationCode(ValidateEntity validateEntity) throws AuthenticationException{

        VerificationCode currentVerificationCode = getVerificationCodeByEmail(validateEntity.getEmail());

        if(currentVerificationCode.getVerificationCode().equals(validateEntity.getValidationCode()) && hasExpired(currentVerificationCode)){

           User user =  currentVerificationCode.getUser();
           verificationRepository.deleteByUser(user);

           user.setUserStatus(User.Status.ACTIVE);
           userRepository.save(user);
        }else {
            throw new AuthenticationException("The validation code which has been provided is incorrect");
        }
    }

    public boolean hasExpired(VerificationCode verificationCode){

        Instant currentTime = new Date(System.currentTimeMillis()).toInstant();

        if(verificationCode.getCreationTimeStamp().until(currentTime,ChronoUnit.MINUTES)<=30){
            return true;
        }

        log.warn("Validation Code has Expired");
        return false;
    }

    public static String generateRandomCode(){
        Random r = new Random();
        int number = r.nextInt(999999);

        return String.format("%06d", number);
    }

    public VerificationCode getVerificationCodeByEmail(String email){
        User currentUser = userRepository.findUsersByEmail(email);
        log.info("User has been found with email "+email);
        return verificationRepository.findVerificationCodeByUser(currentUser);
    }
}
