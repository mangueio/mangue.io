package io.mangue.messaging;

import io.mangue.dtos.MessageDTO;

public interface IPublisher {
    public void publish(MessageDTO message);
}