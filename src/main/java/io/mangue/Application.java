package io.mangue;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import io.mangue.actors.SpringExtension;
import io.mangue.exceptions.MyAsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class Application implements AsyncConfigurer{

    @Value("${async.corePoolSize:5}")
    public Integer corePoolSize; // 5

    @Value("${async.maxPoolSize:30}")
    public Integer maxPoolSize; // 30

    @Value("${async.queueCapacity:15}")
    public Integer queueCapacity; // 15

    @Value("${async.threadNamePrefix:'AsyncExecutor-'}")
    public String threadNamePrefix;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        startActorSystem(context);
    }

    public static void startActorSystem(ApplicationContext context){
        ActorSystem system = context.getBean(ActorSystem.class);
        SpringExtension ext = context.getBean(SpringExtension.class);
        // Use the Spring Extension to create props for a named actor bean
        ActorRef supervisor = system.actorOf(ext.props("supervisor"));
    }

    /**
     * @Async API thread pool
     * */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncUncaughtExceptionHandler();
    }
}
