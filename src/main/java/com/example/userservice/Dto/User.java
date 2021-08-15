package com.example.userservice.Dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@AllArgsConstructor
public class User{

    @Id
    private String id;

    private String firstName, lastName, email, password;
    private Status userStatus;

    public enum Status{
        ACTIVE, PENDING, DISABLED
    }

}
