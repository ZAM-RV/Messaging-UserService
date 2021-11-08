package com.example.userservice.Services.Friends;

import com.example.userservice.Dto.FriendDto.FriendRequest;
import com.example.userservice.Repository.FriendRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidateFriends {

    final private FriendRequestRepository friendRequestRepository;

    @Autowired
    public ValidateFriends(FriendRequestRepository friendRequestRepository) {
        this.friendRequestRepository = friendRequestRepository;
    }

    // TODO: Make sure that you test this feature.
    public boolean doesAFriendRequestAlreadyExist(String requester, String recipient){
        List<FriendRequest> friendRequests = friendRequestRepository.doesAFriendRequestExist(requester, recipient);

        if(friendRequests == null || friendRequests.isEmpty()){
            return false;
        }
        return true;
    }
}
