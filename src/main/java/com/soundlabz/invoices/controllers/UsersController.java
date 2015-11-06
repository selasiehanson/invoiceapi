package com.soundlabz.invoices.controllers;

import com.soundlabz.invoices.domain.User;
import com.soundlabz.invoices.domain.repositories.UserRepository;
import com.soundlabz.invoices.security.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/api/users/current", method = RequestMethod.GET)
    public User getCurrent() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UserAuthentication) {
            return ((UserAuthentication) authentication).getDetails();
        }

        return new User(authentication.getName());
    }
}
