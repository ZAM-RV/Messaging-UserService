package com.example.userservice.Dto;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private final String jwt;
}
