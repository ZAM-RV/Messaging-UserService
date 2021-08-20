package com.example.userservice;

import com.example.userservice.Services.Helpers.GenerateVerificationCode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MessagingUserServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void checkValidationCode(){
        String number = GenerateVerificationCode.generateRandomCode();

        System.out.println(number);
        assert(1==1);
    }

}
