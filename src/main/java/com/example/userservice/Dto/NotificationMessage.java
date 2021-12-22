package com.example.userservice.Dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

import java.util.Date;

@Data
@Builder
@TypeAlias("com.example.messaging.Dto.NotificationMessage")
public class NotificationMessage {
    private String from;
    private String to;
    private Type type;

    public enum Type {
        FRIEND_REQUEST("Friend_request"),
        ACCEPTED_FRIEND_REQUEST("Accepted_friend_request");

        private String value;

        Type(String value) {
            this.value = value;
        }
    }
}

