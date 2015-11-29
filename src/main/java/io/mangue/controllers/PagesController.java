package io.mangue.controllers;

import io.mangue.services.UtilService;
import io.mangue.util.ProfileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by misael on 17/10/2015.
 */
@Controller
public class PagesController {

    @Autowired
    private UtilService utilService;

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String home() {
        if(utilService.applicationProfile() == ProfileType.DEV)
            return "index";
        else
            return "indexmin";

    }

    @RequestMapping(value = "/app/**", method = RequestMethod.GET)
    public String app() { return home(); }

    @RequestMapping(value = "/access/**", method = RequestMethod.GET)
    public String access() { return home(); }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() { return home(); }


    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}
