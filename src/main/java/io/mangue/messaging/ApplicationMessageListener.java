package io.mangue.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.mangue.config.Logger;
import io.mangue.dtos.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.io.IOException;

/**
 * Created by misael on 24/10/2015.
 */
public class ApplicationMessageListener implements MessageListener {

    @Autowired
    private MessageExecutorProxy proxy;

    @Autowired
    @Qualifier("objectMapper")
    private ObjectMapper mapper;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        MessageDTO messageDTO = null;
        try {
            messageDTO = mapper.readValue(message.toString(), MessageDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        proxy.proxy(messageDTO.executor, messageDTO.payload);
    }
}
