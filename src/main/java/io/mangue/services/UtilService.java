package io.mangue.services;

import io.mangue.models.App;
import io.mangue.util.ProfileType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by misael on 19/10/2015.
 */
@Service
public class UtilService {

    @Value("${spring.profiles.active}")
    public String profile;

    public App getAppFromHost(String subdomain){
        // TODO implement db and cache to retreive
        App app = new App();
        app.name = "Admin";
        app.subdomain = "admin";
        return app;
    }

    public ProfileType applicationProfile(){
        if(profile != null && profile.equals("prod"))
            return ProfileType.PROD;
        else
            return ProfileType.DEV;
    }

    public HttpServletRequest getHttpServletRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    public HttpServletResponse getHttpServletResponse(){
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
    }
}
