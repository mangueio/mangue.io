package io.mangue;

import io.mangue.exceptions.MyAsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class Application implements AsyncConfigurer{

    @Value("${async.core-pool-size}")
    public Integer corePoolSize; // 5

    @Value("${async.max-pool-size}")
    public Integer maxPoolSize; // 30

    @Value("${async.queue-capacity}")
    public Integer queueCapacity; // 15

    @Value("${async.thread-name-prefix}")
    public String threadNamePrefix;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Executor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize != null? corePoolSize: 5);
        executor.setMaxPoolSize(maxPoolSize != null? maxPoolSize: 30);
        executor.setQueueCapacity(queueCapacity != null? queueCapacity: 15);
        executor.setThreadNamePrefix(threadNamePrefix != null? threadNamePrefix: "AsyncExecutor-");
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncUncaughtExceptionHandler();
    }
}
