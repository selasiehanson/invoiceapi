package com.soundlabz.invoices.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymController {

    @RequestMapping(value = "/api/thym")
    public String index() {
        return "index";
    }
}
