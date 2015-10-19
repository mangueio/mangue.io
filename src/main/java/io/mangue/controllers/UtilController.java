package io.mangue.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by misael on 18/10/2015.
 */
@Controller
@RequestMapping("/util")
public class UtilController {

    @RequestMapping("/checkUser")
    public String getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object object = null;
        if (auth == null) {
             object = auth.getPrincipal();
        }
        return object + "";
    }
}
