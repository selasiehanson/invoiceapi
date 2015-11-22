package com.soundlabz.invoices.services;

import com.soundlabz.invoices.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class LocalFileManagerImpl implements FileManager {


    private UploadConfiguration uploadConfiguration;

    @Autowired
    public void setUploadConfiguration(UploadConfiguration uploadConfiguration) {
        this.uploadConfiguration = uploadConfiguration;
    }

    @Override
    public String uploadFile(MultipartFile file, String extension) throws IOException {
        byte[] bytes;
        bytes = file.getBytes();
        String newFileName = String.format("%s.%s", new RandomStringGenerator().nextValue(), extension);

        String fullPath = String.format("%s/%s", uploadConfiguration.uploadDirectory, newFileName);
        FileCopyUtils.copy(bytes, new File(fullPath));

        return newFileName;
    }

    @Override
    public byte[] getBytes(String filename) throws IOException {
        String fullPath = String.format("%s/%s", uploadConfiguration.uploadDirectory, filename);
        File file = new File(fullPath);
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream inputStream = new BufferedInputStream(fis);
        byte[] bytes = new byte[(int) file.length()];
        inputStream.read(bytes);
        inputStream.close();
        return bytes;
    }


}
