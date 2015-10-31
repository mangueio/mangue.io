package io.mangue.controllers;

import io.mangue.dtos.MessageDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 * Created by misael on 29/10/2015.
 */
@Controller
public class WebSocket {
    @MessageMapping("/ws")
    public MessageDTO pipe(MessageDTO message) throws Exception {
        return new MessageDTO("Hello, " + message.message + "!");
    }
}
