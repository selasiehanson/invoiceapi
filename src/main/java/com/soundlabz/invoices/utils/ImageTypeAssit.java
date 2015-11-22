package com.soundlabz.invoices.utils;

import java.util.Arrays;

public class ImageTypeAssit {

    public static String basePrefixFromFilename(String filename) {
        //get the extension by picking the last value in the list
        String lastValue = Arrays.asList(filename.split("\\."))
                .stream().reduce((a, b) -> b).get();
        switch (lastValue.toLowerCase()) {
            case "jpeg":
            case "jpg":
                return "data:image/jpeg;base64";
            case "png":
                return "data:image/png;base64";
            default:
                return null;
        }

    }
}
