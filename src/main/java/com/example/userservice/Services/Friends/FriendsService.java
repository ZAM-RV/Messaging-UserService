package com.example.userservice.Services.Friends;

import com.example.userservice.Dto.FriendDto.FriendRequest;
import com.example.userservice.Dto.FriendDto.OtherUser;
import com.example.userservice.Repository.FriendRequestRepository;
import com.example.userservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendsService {

    final private UserRepository userRepository;
    final private FriendRequestRepository friendRequestRepository;

    @Autowired
    public FriendsService(UserRepository userRepository, FriendRequestRepository friendRequestRepository) {
        this.userRepository = userRepository;
        this.friendRequestRepository = friendRequestRepository;
    }

    public List<OtherUser> findActiveUser(String currentUsername) {
        List<OtherUser> allUsers = userRepository.findActiveUsers();
        if (allUsers == null) {
            return null;
        }
        List<OtherUser> allUsersExcludingSelf = allUsers.stream()
                .filter(user -> !user.getUsername().equals(currentUsername))
                .collect(Collectors.toList());

        return allUsersExcludingSelf;
    }

    public void sendFriendRequest(String currentUsername, String friendUsername) {
        FriendRequest friendRequest = FriendRequest.builder()
                .requester(currentUsername)
                .recipient(friendUsername)
                .build();

        friendRequestRepository.save(friendRequest);
    }

}
