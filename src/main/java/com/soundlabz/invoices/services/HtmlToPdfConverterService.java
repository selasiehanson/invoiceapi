package com.soundlabz.invoices.services;

import java.util.Map;

public interface HtmlToPdfConverterService {

    public String convert(Map<String, Object> inputs);
}
