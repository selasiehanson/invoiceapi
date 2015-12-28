package com.soundlabz.invoices.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soundlabz.invoices.controllers.exceptions.ParameterMissingException;
import com.soundlabz.invoices.domain.Invoice;
import com.soundlabz.invoices.domain.repositories.UserCompanyRepository;
import com.soundlabz.invoices.domain.requestobjects.InvoiceRequest;
import com.soundlabz.invoices.services.ImageService;
import com.soundlabz.invoices.services.InvoiceService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController extends BaseController {

    private InvoiceService invoiceService;
    private ImageService imageService;
    private UserCompanyRepository userCompanyRepository;

    @Autowired
    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    @Autowired
    public void setUserCompanyRepository(UserCompanyRepository userCompanyRepository) {
        this.userCompanyRepository = userCompanyRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Invoice> getInvoices() {
        return invoiceService.getInvoices(getCurrentUser());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Invoice findInvoice(@PathVariable Long id) {
        return invoiceService.getInvoice(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Invoice createInvoice(@Valid @RequestBody InvoiceRequest invoiceRequest) {
        return saveOrUpdate(invoiceRequest);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Invoice updateInvoice(@PathVariable Long id, @Valid @RequestBody InvoiceRequest invoiceRequest) {
        if (invoiceRequest.getId() == null) {
            throw new ParameterMissingException("id cannot be null");
        }
        return saveOrUpdate(invoiceRequest);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteInvoice(@PathVariable Long id) {
        if (id == null) {
            throw new ParameterMissingException("id cannot be null");
        }
        invoiceService.deleteInvoice(id);
    }

    private Invoice saveOrUpdate(InvoiceRequest invoiceRequest) {
        if (invoiceRequest.getCurrencyId() == null) {
            throw new ParameterMissingException("Currency Id missing");
        }

        if (invoiceRequest.getClientId() == null) {
            throw new ParameterMissingException("Recipient Id is mising");
        }

        return invoiceService.createOrUpdateInvoice(invoiceRequest, getCurrentUser());
    }



    @RequestMapping(value = "/aspdf", method = RequestMethod.GET)
    public Map<String, String> getPdfOfInvoice(@RequestParam Long invoiceId) {


        Invoice invoice = invoiceService.getInvoice(invoiceId);
        ObjectMapper mapper = new ObjectMapper();

        try {
            String output = mapper.writeValueAsString(invoice);
            System.out.println(output);
            Map<String, Object> inputs =  new HashMap<>();
            inputs.put("invoice", invoice);
            String logoFilename = userCompanyRepository.findByUserId(getCurrentUser().getId()).getLogo();
            String imageDataUri = imageService.getImageAsDataUri(logoFilename);
            inputs.put("logo", imageDataUri);

            byte[] bytes = invoiceService.getPreview(inputs);
            String encodedImageStr = org.apache.tomcat.util.codec.binary.StringUtils.newStringUtf8(Base64.encodeBase64(bytes));
            Map<String, String> dataUri = new HashMap<>();
            dataUri.put("pdf", encodedImageStr);
            return dataUri;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
