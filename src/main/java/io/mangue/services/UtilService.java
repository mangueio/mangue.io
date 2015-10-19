package io.mangue.services;

import io.mangue.models.App;
import org.springframework.stereotype.Service;

/**
 * Created by misael on 19/10/2015.
 */
@Service
public class UtilService {

    public App getAppFromHost(String subdomain){
        // TODO implement db and cache to retreive
        App app = new App();
        app.name = "Admin";
        app.subdomain = "admin";
        return app;
    }
}
