package io.mangue.config;

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
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class RestDataConfig  extends RepositoryRestConfigurerAdapter {

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    super.configureRepositoryRestConfiguration(config);
    config.setBasePath("/data");
  }

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

}