package com.soundlabz.invoices.controllers;

import com.soundlabz.invoices.domain.User;
import com.soundlabz.invoices.domain.UserCompany;
import com.soundlabz.invoices.domain.repositories.UserCompanyRepository;
import com.soundlabz.invoices.domain.repositories.UserRepository;
import com.soundlabz.invoices.security.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UsersController extends BaseController {

    private UserRepository userRepository;

    private UserCompanyRepository userCompanyRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserCompanyRepository(UserCompanyRepository userCompanyRepository) {
        this.userCompanyRepository = userCompanyRepository;
    }

    @RequestMapping(value = "/api/users/current", method = RequestMethod.GET)
    public User getCurrent() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UserAuthentication) {
            return ((UserAuthentication) authentication).getDetails();
        }

        return new User(authentication.getName());
    }

    @RequestMapping(value = "/api/users/company-details", method = RequestMethod.POST)
    public UserCompany updateCompanyDetails(@RequestBody @Valid UserCompany userCompany) {
        userCompany.setUser(getCurrentUser());
        userCompanyRepository.save(userCompany);
        return userCompany;
    }
}
