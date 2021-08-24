package com.example.userservice.Dto;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class ValidateEntity {
    @Email
    private String email;
    private String validationCode;
}
