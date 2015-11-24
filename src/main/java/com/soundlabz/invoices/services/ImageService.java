package com.soundlabz.invoices.services;

import java.io.IOException;

public interface ImageService {
    public String getImageAsDataUri(String logoFilename) throws IOException;
}
