package io.mangue.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by misael on 17/10/2015.
 */
@Controller
@RequestMapping("/")
public class PagesController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }


}
