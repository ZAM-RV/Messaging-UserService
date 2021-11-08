package com.example.userservice.Repository;

import com.example.userservice.Dto.FriendDto.FriendRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRequestRepository extends MongoRepository<FriendRequest, String> {

    @Query("{$or : [{'requester': 'testusername3', 'recipient': 'testusername2'},{'requester': 'testusername2', 'recipient': 'testusername3'}]}")
    public List<FriendRequest> doesAFriendRequestExist(String requestor, String recipient);
}
