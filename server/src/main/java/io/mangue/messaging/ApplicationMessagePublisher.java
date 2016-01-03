package io.mangue.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mangue.dtos.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.Topic;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class ApplicationMessagePublisher implements IPublisher {

    @Autowired
//    @Qualifier("redisTemplate")
    private RedisTemplate<String,Object> redisTemplate;

    private final AtomicLong counter = new AtomicLong( 0 );

    @Autowired
    @Qualifier("applicationChannel")
    private Topic topic;

    @Autowired
    @Qualifier("objectMapper")
    private ObjectMapper mapper;

    @Override
    public void publish(MessageDTO message) {
        String stringMessage = null;
        try {
            stringMessage = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        redisTemplate.convertAndSend(topic.getTopic(), stringMessage);
    }

    @Scheduled( fixedDelay = 5000 )
    public void scheduledPublish() {
        MessageDTO message = new MessageDTO("update", "UpdateCache", "Message " + counter.incrementAndGet() + ", " + Thread.currentThread().getName());
        String stringMessage = null;
        try {
            stringMessage = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        redisTemplate.convertAndSend( topic.getTopic(), stringMessage);
    }

}