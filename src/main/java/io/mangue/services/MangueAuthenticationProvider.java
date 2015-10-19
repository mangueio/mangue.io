package io.mangue.services;

import io.mangue.models.App;
import io.mangue.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;  
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;  
import org.springframework.security.core.AuthenticationException;  
import org.springframework.security.core.userdetails.User;  
import org.springframework.security.core.userdetails.UserDetails;  
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

@Service
public class MangueAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Context
    private HttpServletRequest request;

    @Autowired
    private UtilService utilService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        UserDetails loadedUser = null;
        io.mangue.models.User user = null;

        App app = utilService.getAppFromHost(request.getHeader("Host"));

        try {
             user = userRepository.findByUsername(username);
            loadedUser = new User(user.username, user.password, user.authorities);
        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }

        if(!(user != null && user.appId != null && app != null && app.id.equals(user.appId)))
            throw new BadCredentialsException("User does not belong to app");

        if (loadedUser == null) {
            throw new InternalAuthenticationServiceException(
                    "UserDetailsService returned null, which is an interface contract violation");
        }
        return loadedUser;
    }
}