package com.example.userservice.Dto;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Data
@Document
@Builder
public class VerificationCode {
    @Id
    private String id;
    @NotNull
    private User user;
    @Size(min = 6, max = 6, message = "Verification Code must be 6 digits long")
    private String verificationCode;
    @Builder.Default
    private Instant creationTimeStamp = new Timestamp(System.currentTimeMillis()).toInstant();
    private long timeoutInMinutes;
}

