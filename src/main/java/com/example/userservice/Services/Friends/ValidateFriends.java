package com.example.userservice.Services.Friends;

import com.example.userservice.Dto.FriendDto.FriendRequest;
import com.example.userservice.Repository.FriendRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ValidateFriends {

    final private FriendRequestRepository friendRequestRepository;

    @Autowired
    public ValidateFriends(FriendRequestRepository friendRequestRepository) {
        this.friendRequestRepository = friendRequestRepository;
    }

    public boolean doesAFriendRequestAlreadyExist(String requester, String recipient){
        List<FriendRequest> friendRequests = friendRequestRepository.doesAFriendRequestExist(requester, recipient);

        if(friendRequests == null || friendRequests.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean doesAValidFriendRequestExist(String recipient, String requester){
        return friendRequestRepository.existsByRecipientAndRequester(recipient,requester);
    }

}
