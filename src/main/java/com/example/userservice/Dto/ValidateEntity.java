package com.example.userservice.Dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class ValidateEntity {
    @Email
    private String email;
    @Size(min = 6, max = 6)
    private String validationCode;
}
