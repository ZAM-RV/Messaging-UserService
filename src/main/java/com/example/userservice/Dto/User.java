package com.example.userservice.Dto;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

@Document(collection = "users")
@Data
@Builder
public class User implements UserDetails {

    @Id
    private String id;

    @NotBlank
    @Accessors(fluent = true)
    private String username;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private Date dateOfBirth;

    @NotBlank
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank
    @Size(min = 6, message = "password must at least be 6 characters long")
    private String password;

    private Status userStatus;

    private List<String> friends;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(userStatus == null){
            return Arrays.asList(new SimpleGrantedAuthority("DISABLED_USER"));
        }

        return Arrays.asList(new SimpleGrantedAuthority(userStatus.toString()+"_USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(userStatus == Status.DISABLED || userStatus == null){
            return false;
        }
        return true;

    }

    public boolean areFriends(String friendUsername) {
        if(friends == null || friends.isEmpty()){
            return false;
        }
        return friends.contains(friendUsername.toLowerCase(Locale.ROOT));
    }

    public void addFriend(String username) {
        if(friends == null){
            friends = new ArrayList<>();
        }
        friends.add(username);
    }

    public enum Status{
        ACTIVE, PENDING, DISABLED
    }





}
