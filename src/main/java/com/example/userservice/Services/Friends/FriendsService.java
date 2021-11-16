package com.example.userservice.Services.Friends;

import com.example.userservice.Dao.FriendDao;
import com.example.userservice.Dao.UserDao;
import com.example.userservice.Dto.FriendDto.FriendRequest;
import com.example.userservice.Dto.FriendDto.OtherUser;
import com.example.userservice.Dto.User;
import com.example.userservice.Exceptions.InvalidFriendRequest;
import com.example.userservice.Exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import com.example.userservice.Dto.NotificationMessage;
import com.example.userservice.Repository.FriendRequestRepository;
import com.example.userservice.Repository.UserRepository;
import com.example.userservice.Services.Notifications.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FriendsService {

    private final FriendDao friendDao;
    private final UserDao userDao;
    private final ValidateFriends validateFriends;

    @Autowired
    public FriendsService(FriendDao friendDao, UserDao userDao, ValidateFriends validateFriends) {
        this.friendDao = friendDao;
        this.userDao = userDao;
        this.validateFriends = validateFriends;
    }


    public List<OtherUser> findActiveUser(String currentUsername) {
        List<OtherUser> allUsers = userDao.findActiveUsers();
        if (allUsers == null) {
            return null;
        }

        // Gets all Users excluding the current User
        return allUsers.stream()
                .filter(user -> !user.getUsername().equals(currentUsername))
                .collect(Collectors.toList());
    }

    public void sendFriendRequest(String currentUsername, String friendUsername) throws Exception {

        User friendUser = userDao.findByUsername(friendUsername);

        if (friendUser == null) {
            throw new UserNotFoundException("The user "+ friendUsername+" does not exist");
        }

        if (friendUser.areFriends(currentUsername)) {
            log.info("The User {} and {} are already friends.",currentUsername,friendUsername);
            return;
        }
        if (!validateFriends.doesAFriendRequestAlreadyExist(currentUsername,friendUsername)) {

            log.info("Creating FriendRequest Between {} and {}.",currentUsername,friendUsername);
            FriendRequest friendRequest = FriendRequest.builder()
                    .requester(currentUsername)
                    .recipient(friendUsername)
                    .build();

            friendDao.save(friendRequest);
            return;
        }
        log.info("There already exists a friend request between {} and {}.", currentUsername, friendUsername);
    }

    public void acceptFriendRequest(User currentUser, String requestorUsername) throws Exception {
        if (currentUser == null || requestorUsername == null) {
            throw new UserNotFoundException("The User(s) could not be found");
        }
        if(currentUser.areFriends(requestorUsername)){
            log.info("The current User is already friends with {}", requestorUsername);
            return;
        }

        if(validateFriends.doesAValidFriendRequestExist(currentUser.username(), requestorUsername)) {
            User requestorUser = userDao.findByUsername(requestorUsername);
            currentUser.addFriend(requestorUsername);
            requestorUser.addFriend(currentUser.username());
            userDao.save(requestorUser);
            userDao.save(currentUser);

            friendDao.deleteFriendRequest(currentUser.username(),requestorUsername);
            return;
        }
        throw new InvalidFriendRequest("There is no friend request from " +requestorUsername +" to " +currentUser.username());
    }

    public void rejectFriendRequest(User currentUser, String requesterUsername) throws Exception{
        if (currentUser == null || requesterUsername == null) {
            throw new UserNotFoundException("The User(s) could not be found");
        }
        if(currentUser.areFriends(requesterUsername)){
            log.info("The current User is already friends with {}", requesterUsername);
            return;
        }
        friendDao.rejectFriendRequest(currentUser.username(), requesterUsername);
    }

    public List<OtherUser> allPendingRequests(User currentUser){
        List<FriendRequest> allPendingRequests = friendDao.viewAllPendingRequest(currentUser.username());

        if(allPendingRequests == null) {
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();

        return allPendingRequests.stream().map(request -> userDao.findByUsername(request.getRequester()))
                .map(user -> modelMapper.map(user, OtherUser.class)).collect(Collectors.toList());
    }

    public List<OtherUser> allRejectedRequests(User currentUser){
        List<FriendRequest> allPendingRequests = friendDao.viewAllRejectedRequest(currentUser.username());

        if(allPendingRequests == null) {
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();

        return allPendingRequests.stream().map(request -> userDao.findByUsername(request.getRequester()))
                .map(user -> modelMapper.map(user, OtherUser.class)).collect(Collectors.toList());

    }

}
