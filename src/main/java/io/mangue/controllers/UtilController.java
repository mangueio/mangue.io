package io.mangue.controllers;

import io.mangue.models.User;
import io.mangue.services.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.listener.Topic;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created by misael on 18/10/2015.
 */
@RestController
@RequestMapping("/util")
public class UtilController {
//
//    @Context
//    private HttpServletRequest request;
//
//    @Context
//    private UriInfo uriInfo;

    @Autowired
    @Qualifier("sessionRegistry")
    private SessionRegistry sessionRegistry;

    @Autowired
    private AsyncService asyncService;

    @Autowired
    @Qualifier("applicationChannel")
    private Topic topic;

    @RequestMapping(value = "/checkUser",
            produces = MediaType.APPLICATION_JSON,
            method = RequestMethod.GET)
    public User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object object = null;
        if (auth != null) {
             object = auth.getPrincipal();
        }

        List<Object> ps =  sessionRegistry.getAllPrincipals();
        if(object instanceof User)
            return (User) object;
        return (User)object;
    }

    @RequestMapping("/topic")
    public String getTopic(){
        Assert.isTrue(topic != null && "pubsub:application".equals(topic.getTopic()));
        return topic.getTopic();
    }

    @RequestMapping("/asyncTest")
    public String asyncTest(){
        asyncService.asyncTest();
        return "ok";
    }
}
