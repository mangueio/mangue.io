package io.mangue.messaging;

import io.mangue.config.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by misael on 24/11/2015.
 */
@Component("UpdateCache")
public class UpdateCacheExecutor implements MessageExecutor {

    @Override
    public void execute(Object message) {
        Logger.info(message + "");
    }
}
