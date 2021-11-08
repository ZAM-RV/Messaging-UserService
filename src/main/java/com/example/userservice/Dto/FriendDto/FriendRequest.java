package com.example.userservice.Dto.FriendDto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@Document(collection = "friendRequests")
public class FriendRequest {
    @Id
    private String id;
    @NotBlank
    private String requester;
    @NotBlank
    private String recipient;

    private int status;
}
