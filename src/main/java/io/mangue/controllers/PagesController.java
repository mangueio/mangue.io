package io.mangue.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mangue.aspect.SubdomainTypes;
import io.mangue.aspect.annotations.SubdomainMapping;
import io.mangue.config.multitenance.TenantContextHolder;
import io.mangue.dtos.SystemInfoDTO;
import io.mangue.models.User;
import io.mangue.services.UtilService;
import io.mangue.util.ProfileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by misael on 17/10/2015.
 */
@Controller
public class PagesController {

    @Value("${mangue.domain:'mangue.com'}") // mangue.com is used for development in local proxy server.
    private String domain;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UtilService utilService;

    @Autowired
    private ObjectMapper mapper;

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public ModelAndView home() throws JsonProcessingException {
        User user = utilService.getUser();

        ModelAndView mav = new ModelAndView();

        SystemInfoDTO systemInfoDTO = new SystemInfoDTO();
        systemInfoDTO.domain = domain;

        mav.addObject("user", user);
        mav.addObject("userJson", user != null ? mapper.writeValueAsString(user) : "null");
        mav.addObject("systemInfoJson", mapper.writeValueAsString(systemInfoDTO));

        if(!TenantContextHolder.isTenantConsoleRequest() && TenantContextHolder.getTenantSubdomain() == null) {
            mav.setViewName("manguehome");
            return mav; // if not console and no subdomain
        }else if(request.getServletPath().equals("/") && TenantContextHolder.getTenantSubdomain() == null) {
            mav.setViewName("redirect:/apps"); // if console and path "/"
            return mav;
        }

        if(utilService.applicationProfile() == ProfileType.DEV) {
            mav.setViewName("index");
            return mav;
        }else {
            mav.setViewName("indexmin");
            return mav;
        }
    }

    @PreAuthorize("hasAuthority('SUPERUSER') or hasAuthority('ADMIN')")
    @RequestMapping(value = "/apps" , method = RequestMethod.GET)
    @SubdomainMapping(SubdomainTypes.CONSOLE)
    public ModelAndView apps() throws JsonProcessingException {
        return  home();
    }

    @RequestMapping(value = "/users/**", method = RequestMethod.GET)
    @SubdomainMapping(SubdomainTypes.CONSOLE)
    public ModelAndView users() throws JsonProcessingException {
        return home();
    }

    @RequestMapping(value = "/app/**", method = RequestMethod.GET)
    public ModelAndView app() throws JsonProcessingException { return home(); }

    @RequestMapping(value = "/access/**", method = RequestMethod.GET)
    @SubdomainMapping(SubdomainTypes.ALL)
    public ModelAndView access() throws JsonProcessingException { return home(); }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @SubdomainMapping(SubdomainTypes.CONSOLE)
    public ModelAndView login() throws JsonProcessingException { return home(); }

    @PreAuthorize("hasAuthority('SUPERUSER') or hasAuthority('ADMIN')")
    @RequestMapping(value = {"/{appSubdomain}/*"} , method = RequestMethod.GET)
    @SubdomainMapping(SubdomainTypes.CONSOLE)
    public ModelAndView appPages(@PathVariable("appSubdomain") String appSubdomain) throws JsonProcessingException {
        return  home();
    }

    @PreAuthorize("hasAuthority('SUPERUSER') or hasAuthority('ADMIN')")
    @RequestMapping(value = "/{appSubdomain}" , method = RequestMethod.GET)
    @SubdomainMapping(SubdomainTypes.CONSOLE)
    public ModelAndView app(@PathVariable("appSubdomain") String appSubdomain) throws JsonProcessingException {
        return  home();
    }

}
