package com.example.userservice.Repository;

import com.example.userservice.Dto.FriendDto.FriendRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRequestRepository extends MongoRepository<FriendRequest, String> {

    @Query("{$or : [{'requester': ?0, 'recipient': ?1},{'requester': ?1, 'recipient': ?0}]}")
    List<FriendRequest> doesAFriendRequestExist(String requester, String recipient);

    boolean existsByRecipientAndRequester(String recipient, String requester);
    
    FriendRequest findByRecipientAndRequester(String recipient, String requester);

    void deleteByRecipientAndRequester(String recipient, String requester);

    List<FriendRequest> findByRecipientAndStatus(String recipient, int status);

}
