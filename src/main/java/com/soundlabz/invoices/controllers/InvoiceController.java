package com.soundlabz.invoices.controllers;

import com.soundlabz.invoices.controllers.exceptions.ParameterMissingException;
import com.soundlabz.invoices.domain.Invoice;
import com.soundlabz.invoices.domain.requestobjects.InvoiceRequest;
import com.soundlabz.invoices.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private InvoiceService invoiceService;

    @Autowired
    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Invoice> getInvoices() {
        return invoiceService.getInvoices();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Invoice findInvoice(@PathVariable Long id) {
        return invoiceService.getInvoice(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    Invoice createInvoice(@Valid @RequestBody InvoiceRequest invoiceRequest) {
        return saveOrUpdate(invoiceRequest);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Invoice updateInvoice(@PathVariable Long id, @Valid @RequestBody InvoiceRequest invoiceRequest) {
        return saveOrUpdate(invoiceRequest);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void updateInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
    }

    private Invoice saveOrUpdate(InvoiceRequest invoiceRequest) {
        if (invoiceRequest.getCurrencyId() == null) {
            throw new ParameterMissingException("Currency Id missing");
        }

        if (invoiceRequest.getRecipientId() == null) {
            throw new ParameterMissingException("Recipeint Id is mising");
        }

        return invoiceService.createOrUpdateInvoice(invoiceRequest);
    }

    @RequestMapping(value = "/aspdf", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getPdfOfInvoice() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = "preview.pdf";

        //downloads the pdf
        //headers.setContentDispositionFormData(filename, filename);

        //shows pdf inline
        headers.add("content-disposition", "inline;filename=" + filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        try {
            return new ResponseEntity<byte[]>(invoiceService.getPreview(), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
