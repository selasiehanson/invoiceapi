package com.soundlabz.invoices.services;

import com.soundlabz.invoices.utils.ImageTypeAssit;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService{

    private FileManager fileManager;

    @Autowired
    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public String getImageAsDataUri(String logoFilename) throws IOException {

        byte[] bytes = fileManager.getBytes(logoFilename);

        String encodedImageStr = org.apache.tomcat.util.codec.binary.StringUtils.newStringUtf8(Base64.encodeBase64(bytes));
        String prefix = ImageTypeAssit.basePrefixFromFilename(logoFilename);
        String imageUri = String.format("%s,%s", prefix, encodedImageStr);

        return imageUri;
    }
}
