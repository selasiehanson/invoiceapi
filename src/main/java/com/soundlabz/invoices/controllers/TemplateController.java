package com.soundlabz.invoices.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/templates")
public class TemplateController {


    @RequestMapping(value = "/invoices/preview", method = RequestMethod.GET)
    public String getInvoicePreview() {
        return "invoice";
    }
}
