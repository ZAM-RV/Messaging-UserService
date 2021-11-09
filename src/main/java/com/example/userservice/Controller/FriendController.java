package com.example.userservice.Controller;

import com.example.userservice.Dto.FriendDto.OtherUser;
import com.example.userservice.Dto.User;
import com.example.userservice.Services.Friends.FriendsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

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
    public ResponseEntity<?> getAllUsers(@AuthenticationPrincipal User user) {
        List<OtherUser> users = friendsService.findActiveUser(user.username());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/sendrequest")
    public ResponseEntity<?> addFriend(@AuthenticationPrincipal User user, @RequestParam("username") String friendsUsername) {
        if(friendsUsername == null) {
            return new ResponseEntity<>("There is no username in this friend request",HttpStatus.BAD_REQUEST);
        }

        try{
            friendsService.sendFriendRequest(user.username(), friendsUsername.toLowerCase(Locale.ROOT));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            log.info("Error while sending friend request: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/acceptrequest")
    public ResponseEntity<?> acceptFriend(@AuthenticationPrincipal User user, @RequestParam("username") String username) {
        try{
            friendsService.acceptFriendRequest(user, username.toLowerCase(Locale.ROOT));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            log.info("Error while accepting friend request: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("rejectrequest")
    public ResponseEntity<?> rejectRequest(@AuthenticationPrincipal User user, @RequestParam("username") String username) {
        try{
            friendsService.rejectFriendRequest(user, username.toLowerCase(Locale.ROOT));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            log.info("Error while rejecting friend request: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("pendingrequests")
    public ResponseEntity<?> pendingRequests(@AuthenticationPrincipal User user){
        List<OtherUser> users = friendsService.allPendingRequests(user);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("rejectedrequests")
    public ResponseEntity<?> rejectedRequests(@AuthenticationPrincipal User user){
        List<OtherUser> users = friendsService.allRejectedRequests(user);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }


}
