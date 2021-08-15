package com.example.userservice.Dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class VerificationCode {
    @Id
    private String id;
    private User user;
    @Digits(integer = 6, fraction = 0, message = "Verification Code must be 6 digits long")
    private int verificationCode;
    private Date creationDate;
    private int timeoutInMinutes;
}
