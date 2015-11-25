package io.mangue.controllers;

import io.mangue.messaging.MessageExecutorProxy;
import io.mangue.models.App;
import io.mangue.models.User;
import io.mangue.repositories.AppRepository;
import io.mangue.services.AsyncService;
import io.mangue.services.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.endpoint.InfoEndpoint;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.Topic;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

    @Autowired private RedisTemplate< String, Object > template;

    @Autowired private MessageExecutorProxy mProxy;

    @Autowired
    @Qualifier("sessionRegistry")
    private SessionRegistry sessionRegistry;

    @Autowired
    private AsyncService asyncService;

    @Autowired
    private UtilService utilService;

    @Autowired
    private AppRepository appRepository;

    @Autowired
    @Qualifier("applicationChannel")
    private Topic topic;

    @Autowired
    private InfoEndpoint infoEndpoint;

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

    @PreAuthorize("hasAuthority('SUPERUSER')")
    @CrossOrigin
    @RequestMapping(value = "/newApp", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public App createApp(@RequestBody App app){
        appRepository.save(app);
        return app;
    }

    @RequestMapping("/asyncTest")
    public String asyncTest(){
        mProxy.proxy("UpdateCache", "test");
        asyncService.asyncTest();
        return "ok";
    }

    @RequestMapping(value = "/testRedisTemplate", method = RequestMethod.GET)
    public String  testRedisTemplate(){
        String random = UUID.randomUUID().toString();
        template.opsForValue().set(random , "val" );
        template.expire( random, 10, TimeUnit.SECONDS );
        return "ok";
    }

    @RequestMapping(value = "/getGit", method = RequestMethod.GET)
    public Map<String, Object> getGit(){
        return infoEndpoint.invoke();
    }
}