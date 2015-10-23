package io.mangue.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * Created by misael on 18/10/2015.
 */
@Component
@Path("/util")
@Produces(MediaType.APPLICATION_JSON)
public class UtilController {

    @Context
    private HttpServletRequest request;

    @Context
    private UriInfo uriInfo;

    @RequestMapping("/checkUser")
    public String getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object object = null;
        if (auth != null && request != null) {
             object = auth.getPrincipal();
        }
        return object + "";
    }
}
