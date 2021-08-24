package com.example.userservice.Services.Registration;

import com.example.userservice.Dto.User;
import com.example.userservice.Dto.VerificationCode;
import com.example.userservice.Repository.UserRepository;
import com.example.userservice.Repository.VerificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Locale;

@Service
@Slf4j
public class RegisterService {

    private UserRepository userRepository;
    private VerificationRepository verificationRepository;
    private EmailService emailService;

    @Autowired
    public RegisterService(UserRepository userRepository, VerificationRepository verificationRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.verificationRepository = verificationRepository;
        this.emailService = emailService;
    }

    public void registerNewUser(User user){

        user.setUserStatus(User.Status.PENDING);
        String email = user.getEmail().toLowerCase(Locale.ROOT);
        user.setEmail(email);
        userRepository.save(user);

        verifyNewUser(user);
    }

    public void verifyNewUser(User user){
        VerificationCode verificationCode = VerificationCode.builder()
                .user(user)
                .timeoutInMinutes(30)
                .verificationCode(VerificationCodeService.generateRandomCode())
                .build();

        verificationRepository.save(verificationCode);

        try{
            emailService.sendEmail(user.getEmail(),verificationCode.getVerificationCode());
        } catch (MessagingException e){
            log.warn("Email has failed to be sent to the recipient", e);
        }
    }
}
