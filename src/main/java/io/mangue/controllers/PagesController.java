package io.mangue.controllers;

import io.mangue.aspect.SubdomainTypes;
import io.mangue.aspect.annotations.SubdomainMapping;
import io.mangue.config.multitenance.TenantContextHolder;
import io.mangue.services.UtilService;
import io.mangue.util.ProfileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by misael on 17/10/2015.
 */
@Controller
public class PagesController {

    @Autowired
    private UtilService utilService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String home() {
        if(!TenantContextHolder.isTenantConsoleRequest() && TenantContextHolder.getTenantSubdomain() == null)
            return "manguehome"; // if not console and no subdomain
        else if(request.getServletPath().equals("/") && TenantContextHolder.getTenantSubdomain() == null)
            return "redirect:/apps"; // if console and path "/"

        if(utilService.applicationProfile() == ProfileType.DEV)
            return "index";
        else
            return "indexmin";
    }

    @PreAuthorize("hasAuthority('SUPERUSER')")
    @RequestMapping(value = "/apps" , method = RequestMethod.GET)
    @SubdomainMapping(SubdomainTypes.CONSOLE)
    public String apps(){
        return  home();
    }

    @RequestMapping(value = "/users/**", method = RequestMethod.GET)
    @SubdomainMapping(SubdomainTypes.APP)
    public String users() {
        return home();
    }

    @RequestMapping(value = "/app/**", method = RequestMethod.GET)
    public String app() { return home(); }

    @RequestMapping(value = "/access/**", method = RequestMethod.GET)
    @SubdomainMapping(SubdomainTypes.ALL)
    public String access() { return home(); }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @SubdomainMapping(SubdomainTypes.CONSOLE)
    public String login() { return home(); }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}
