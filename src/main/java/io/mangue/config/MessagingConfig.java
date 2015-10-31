package io.mangue.config;

import com.sun.jndi.ldap.pool.PooledConnectionFactory;
import io.mangue.messaging.ApplicationMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by misael on 27/10/2015.
 */
@Configuration
public class MessagingConfig {

    // -------- redis application level message bus
    @Bean
    public MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter( new ApplicationMessageListener() );
    }

    @Bean
    public RedisMessageListenerContainer redisContainer(JedisConnectionFactory jcf) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setTaskExecutor(taskExecutor());
        container.setConnectionFactory(jcf);
        container.addMessageListener(messageListener(), topic());
        return container;
    }

    @Bean(name = "redisTaskExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setQueueCapacity(1);
        taskExecutor.setCorePoolSize(3);
        return taskExecutor;
    }

    @Bean(name = "applicationChannel")
    public Topic topic() {
        return new ChannelTopic("pubsub:application");
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jcf) throws Exception {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(jcf);
        return redisTemplate;
    }
    // ---------- redis application level message bus

//    @Bean
//    ConnectionFactory jmsConnectionFactory() {
//        // use a pool for ActiveMQ connections
//        PooledConnectionFactory pool = new PooledConnectionFactory();
//        pool.setConnectionFactory(new ActiveMQConnectionFactory("tcp://localhost:61616"));
//        return pool;
//    }

}
