package com.example.userservice.Repository;

import com.example.userservice.Dto.User;
import com.example.userservice.Dto.VerificationCode;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VerificationRepository extends MongoRepository<VerificationCode, String> {

    VerificationCode findVerificationCodeByUser(User user);

    Long deleteByUser(User user);

}
