package com.soundlabz.invoices.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UploadConfiguration {

    @Value("${upload.directory}")
    public String uploadDirectory;
}
