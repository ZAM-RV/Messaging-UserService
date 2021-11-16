package com.example.userservice.Dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class NotificationMessage {
    private String from;
    private String to;
    private Type type;
    private Date creationDate;

    public static enum Type {
        FRIEND_REQUEST("Friend_request"),
        ACCEPTED_FRIEND_REQUEST("Accepted_friend_request");

        private String value;

        Type(String value) {
            this.value = value;
        }
    }
}

