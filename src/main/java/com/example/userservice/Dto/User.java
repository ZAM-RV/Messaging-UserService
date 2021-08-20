package com.example.userservice.Dto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Document(collection = "users")
@Data
@Builder
public class User{

    @Id
    private String id;

    @NotBlank
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    @Email(message = "Please provide a valid email address")
    private String email;
    @NotNull
    @Size(min = 6, message = "password must at least be 6 characters long")
    private String password;

    private Status userStatus;

    private List<User> friends;

    public enum Status{
        ACTIVE, PENDING, DISABLED
    }



}
