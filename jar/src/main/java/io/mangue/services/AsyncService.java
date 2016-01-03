package io.mangue.services;

import io.mangue.config.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by misael on 23/10/2015.
 */
@Service
public class AsyncService {

    @Async
    public void asyncTest(){
        try {
            Thread.sleep(5000);
            Logger.info("Isso é um teste");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
