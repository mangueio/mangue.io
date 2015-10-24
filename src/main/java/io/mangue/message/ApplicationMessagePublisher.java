package io.mangue.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.Topic;
import org.springframework.stereotype.Component;
 
@Component
public class ApplicationMessagePublisher implements IPublisher {
 
    @Autowired
//    @Qualifier("redisTemplate")
    private RedisTemplate<String,Object> redisTemplate;
 
    @Autowired
    @Qualifier("applicationChannel")
    private Topic topic;
 
    @Override
    public void publish(Object message) {
        redisTemplate.convertAndSend(topic.getTopic(),message.toString());
    }
}