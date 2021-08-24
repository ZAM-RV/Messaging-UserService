package com.example.userservice.Services.Registration;

import com.example.userservice.Dto.User;
import com.example.userservice.Dto.VerificationCode;
import com.example.userservice.Repository.UserRepository;
import com.example.userservice.Repository.VerificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Random;

@Service
@Slf4j
public class VerificationCodeService {

    private UserRepository userRepository;
    private VerificationRepository verificationRepository;

    @Autowired
    public VerificationCodeService(UserRepository userRepository, VerificationRepository verificationRepository) {
        this.userRepository = userRepository;
        this.verificationRepository = verificationRepository;
    }

    public void resendVerification(String email){
        User currentUser = userRepository.findUsersByEmail(email);
        log.info("User has been found with email "+email);
        VerificationCode oldVerificationCode = verificationRepository.findVerificationCodeByUser(currentUser);

        log.info("Old verification code has been found for that user");
        oldVerificationCode.setCreationTimeStamp(new Timestamp(System.currentTimeMillis()).toInstant());
        oldVerificationCode.setVerificationCode(generateRandomCode());

        verificationRepository.save(oldVerificationCode);
    }

    public static String generateRandomCode(){
        Random r = new Random();
        int number = r.nextInt(999999);

        return String.format("%06d", number);
    }
}
