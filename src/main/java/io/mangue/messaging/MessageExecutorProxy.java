package io.mangue.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by misael on 24/11/2015.
 */
@Component
public class MessageExecutorProxy {
    @Autowired
    private ApplicationContext applicationContext;

    public void proxy(String executorName, Object message){
        MessageExecutor executor = applicationContext.getBean(executorName, MessageExecutor.class);
        executor.execute(message);
    }
}
