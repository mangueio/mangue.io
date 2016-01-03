package io.mangue.services;

import io.mangue.models.User;
import io.mangue.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class MangueAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private UtilService utilService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        User user = null;

//        App app = utilService.getAppFromHost(request.getHeader("Host"));

        try {
            user = userRepository.findByUsername(username);
        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }

//        if(!(user != null && user.appId != null && app != null && app.id.equals(user.appId)))
//            throw new BadCredentialsException("User does not belong to app");

        if (user == null) {
            //throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
            throw new InternalAuthenticationServiceException("No user found");
        }

        if(user != null && user.password != null && !user.password.equals(authentication.getCredentials().toString())){
            throw new AuthenticationServiceException("Wrong credentials");
        }

        return user;
    }

    public User getUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (auth != null) {
            Object object = auth.getPrincipal();
            if(object instanceof User)
                user = (User) object;
        }
        return user;
    }
}