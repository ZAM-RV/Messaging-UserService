package com.example.userservice.Services.Registration;

import com.example.userservice.Dto.User;
import com.example.userservice.Dto.VerificationCode;
import com.example.userservice.Repository.UserRepository;
import com.example.userservice.Repository.VerificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RegisterService {

    private UserRepository userRepository;
    private VerificationCodeService verificationCodeService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterService(UserRepository userRepository, VerificationCodeService verificationCodeService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.verificationCodeService = verificationCodeService;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerNewUser(User user) {

        user.setUserStatus(User.Status.PENDING);
        String email = user.getEmail().toLowerCase(Locale.ROOT);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(email);

        userRepository.save(user);

        verificationCodeService.verifyNewUser(user);
    }
}
