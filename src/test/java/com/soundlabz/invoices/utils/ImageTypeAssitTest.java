package com.soundlabz.invoices.utils;

import org.junit.Test;
import static org.junit.Assert.*;

public class ImageTypeAssitTest {

    @Test
    public void testBasePrefixFromFilename() throws Exception {
        String jpegBaseStr1 = ImageTypeAssit.basePrefixFromFilename("somfile.jpg");
        String jpegBaseStr2 = ImageTypeAssit.basePrefixFromFilename("somfile.jpeg");
        String pngBaseStr = ImageTypeAssit.basePrefixFromFilename("somfile.png");
        String expectedJPEGBaseStr = "data:image/jpeg;base64";
        String expectedPNGBaseStr = "data:image/png;base64";

        assertEquals (expectedJPEGBaseStr, jpegBaseStr1);
        assertEquals (expectedJPEGBaseStr, jpegBaseStr2 );
        assertEquals (expectedPNGBaseStr, pngBaseStr);
    }
}