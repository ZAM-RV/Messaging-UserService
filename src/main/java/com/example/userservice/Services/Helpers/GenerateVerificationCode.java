package com.example.userservice.Services.Helpers;

import java.util.Random;

public class GenerateVerificationCode {



    public static String generateRandomCode(){
        Random r = new Random();
        int number = r.nextInt(999999);

        return String.format("%06d", number);
    }
}
