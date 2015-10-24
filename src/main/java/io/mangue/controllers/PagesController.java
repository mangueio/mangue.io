package io.mangue.controllers;

import io.mangue.services.UtilService;
import io.mangue.util.ProfileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by misael on 17/10/2015.
 */
@Controller
public class PagesController {

    @Autowired
    private UtilService utilService;

    @RequestMapping("/")
    public String home() {
        if(utilService.applicationProfile() == ProfileType.DEV)
            return "indexmin";
        else
            return "index";
    }


    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}
