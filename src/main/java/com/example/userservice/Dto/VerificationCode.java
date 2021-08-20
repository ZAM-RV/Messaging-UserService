package com.example.userservice.Dto;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Document
@Builder
public class VerificationCode {
    @Id
    private String id;
    @NotNull
    private User user;
    @Digits(integer = 6, fraction = 0, message = "Verification Code must be 6 digits long")
    private int verificationCode;
    private Date creationDate;
    private int timeoutInMinutes;
}
