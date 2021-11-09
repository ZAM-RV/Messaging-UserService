package com.example.userservice.Dao;

import com.example.userservice.Dto.FriendDto.OtherUser;
import com.example.userservice.Dto.User;
import com.example.userservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDao {
    private final UserRepository userRepository;

    @Autowired
    public UserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<OtherUser> findActiveUsers() { return userRepository.findActiveUsers();}

    public User findByUsername(String username){
        if (username == null || username.isBlank()) {
            return null;
        }
        return userRepository.findUserByUsername(username);
    }

    public void save(User user){
        if (user != null) {
            userRepository.save(user);
        }
    }
}
