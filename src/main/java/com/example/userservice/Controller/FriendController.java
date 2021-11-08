package com.example.userservice.Controller;

import com.example.userservice.Dto.FriendDto.OtherUser;
import com.example.userservice.Dto.User;
import com.example.userservice.Services.Friends.FriendsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friend")
@Slf4j
public class FriendController {

    private FriendsService friendsService;

    @Autowired
    public FriendController(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    @GetMapping("/activeUsers")
    public ResponseEntity<?> getAllUsers(@AuthenticationPrincipal User principal) {
        List<OtherUser> users = friendsService.findActiveUser(principal.username());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/sendrequest")
    public ResponseEntity<?> addFriend(@RequestParam("username") String friendsUsername, @AuthenticationPrincipal User principal) {
        if(friendsUsername == null) {
            return new ResponseEntity<String>("There is no username in this friend request",HttpStatus.BAD_REQUEST);
        }

        friendsService.sendFriendRequest(principal.username(), friendsUsername);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
