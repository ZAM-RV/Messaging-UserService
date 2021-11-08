package com.example.userservice.Repository;

import com.example.userservice.Dto.FriendDto.OtherUser;
import com.example.userservice.Dto.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findUsersByEmail(String email);

    @Query(value = "{'userStatus': 'ACTIVE'}", fields = "{'firstName': 1, 'lastName': 1, 'username': 1}")
    List<OtherUser> findActiveUsers();
}
