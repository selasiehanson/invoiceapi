package com.soundlabz.invoices.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileManager {
    public String uploadFile(MultipartFile file, String extension) throws IOException;

    public byte[] getBytes(String filename) throws IOException;
}
