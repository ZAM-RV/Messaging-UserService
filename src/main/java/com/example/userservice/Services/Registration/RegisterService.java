package com.example.userservice.Services.Registration;

import com.example.userservice.Dto.User;
import com.example.userservice.Dto.VerificationCode;
import com.example.userservice.Repository.UserRepository;
import com.example.userservice.Repository.VerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class RegisterService {

    private UserRepository userRepository;
    private VerificationRepository verificationRepository;

    @Autowired
    public RegisterService(UserRepository userRepository, VerificationRepository verificationRepository) {
        this.userRepository = userRepository;
        this.verificationRepository = verificationRepository;
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
    }
}
