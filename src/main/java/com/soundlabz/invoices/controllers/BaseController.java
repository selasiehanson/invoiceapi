package com.soundlabz.invoices.controllers;

import com.soundlabz.invoices.domain.User;
import com.soundlabz.invoices.security.UserAuthentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {

    protected User getCurrentUser() {
        UserAuthentication authentication = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
        return authentication.getDetails();
    }
}
