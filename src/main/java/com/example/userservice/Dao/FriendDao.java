package com.example.userservice.Dao;

import com.example.userservice.Dto.FriendDto.FriendRequest;
import com.example.userservice.Repository.FriendRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FriendDao {

    private final FriendRequestRepository friendRequestRepository;

    @Autowired
    public FriendDao(FriendRequestRepository friendRequestRepository) {
        this.friendRequestRepository = friendRequestRepository;
    }

    public void save(FriendRequest friendRequest) {
        if(friendRequest==null) {
            return;
        }
        friendRequestRepository.save(friendRequest);
    }

    public void deleteFriendRequest(String recipient, String requestor) {
        friendRequestRepository.deleteByRecipientAndRequester(recipient,requestor);
    }

    public void rejectFriendRequest(String recipient, String requestor) {
        FriendRequest friendRequest = friendRequestRepository.findByRecipientAndRequester(recipient, requestor);
        if (friendRequest == null) {
            log.info("There is no friend request from {} to {}", requestor, recipient);
            return;
        }
        friendRequest.setStatus(2);
        friendRequestRepository.save(friendRequest);
    }

    public List<FriendRequest> viewAllPendingRequest(String recipient) {
        if(recipient == null){
            return null;
        }
        return friendRequestRepository.findByRecipientAndStatus(recipient, 0);
    }

    public List<FriendRequest> viewAllRejectedRequest(String recipient) {
        if(recipient == null){
            return null;
        }
        return friendRequestRepository.findByRecipientAndStatus(recipient, 2);
    }
}
