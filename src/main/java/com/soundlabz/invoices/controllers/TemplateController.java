package com.soundlabz.invoices.controllers;

import com.soundlabz.invoices.domain.repositories.InvoiceRepository;
import com.soundlabz.invoices.domain.repositories.UserCompanyRepository;
import com.soundlabz.invoices.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/templates")
public class TemplateController extends BaseController {

    private UserCompanyRepository userCompanyRepository;
    private InvoiceRepository invoiceRepository;

    private ImageService imageService;

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    @Autowired
    public void setUserCompanyRepository(UserCompanyRepository userCompanyRepository) {
        this.userCompanyRepository = userCompanyRepository;
    }

    @Autowired
    public void setInvoiceRepository(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @RequestMapping(value = "/invoices/preview", method = RequestMethod.GET)
    public String getInvoicePreview(@RequestParam Long invoiceId, Model model) {
//        model.addAttribute("toPdf", true);
        String logoFilename = userCompanyRepository.findByUserId(getCurrentUser().getId()).getLogo();

        try {
            String dataUri = imageService.getImageAsDataUri(logoFilename);
            model.addAttribute("logo", dataUri);
            model.addAttribute("invoice", invoiceRepository.findOne(invoiceId));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "invoice";
    }
}