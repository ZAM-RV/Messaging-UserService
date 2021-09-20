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
    private VerificationCodeService verificationCodeService;

    @Autowired
    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerNewUser(User user){

        user.setUserStatus(User.Status.PENDING);
        String email = user.getEmail().toLowerCase(Locale.ROOT);
        StringBuilder newPassword = new StringBuilder("{noop}");
        newPassword.append(user.getPassword());
        user.setPassword(newPassword.toString());
        user.setEmail(email);
        userRepository.save(user);

        verificationCodeService.verifyNewUser(user);
    }
}
