package com.example.userservice.Services.Notifications;

import com.example.userservice.Dto.NotificationMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    private RedisTemplate redisTemplate;

    @Autowired
    public NotificationService(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean validateNotification(NotificationMessage message){

        if(message == null){
            log.error("The message is null, not processing notification");
            return false;
        }
        if(message.getTo() == null){
            log.error("There must be a toUsername for any notification");
            return false;
        }
        if(message.getType() == null){
            log.error("There must be a notification type");
            return false;
        }
        return true;
    }

    public void sendNotification(NotificationMessage message){
        if(validateNotification(message)){
            ObjectRecord<String, NotificationMessage> record = StreamRecords.newRecord().in("my-stream").ofObject(message);
            redisTemplate.opsForStream().add(record);
        }

        log.warn("Failed notification");
    }
}
