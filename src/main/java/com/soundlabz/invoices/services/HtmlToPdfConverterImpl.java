package com.soundlabz.invoices.services;

import com.soundlabz.invoices.components.HtmlToPdfConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

@Service
public class HtmlToPdfConverterImpl implements HtmlToPdfConverterService {

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public String convert() {
        final Context ctx = new Context();
        ctx.setVariable("name", "Kojo");
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));

//        String templateFolder = getClass().getResource("templates").getPath();
        String bunldeJs = "http://localhost:9000/bundle.js";
        String style = "http://localhost:9000/style.css";
        ctx.setVariable("bundleJS", bunldeJs);
        ctx.setVariable("style", style);

        String htmlContent = templateEngine.process("invoice", ctx);
        HtmlToPdfConverter htmlToPdfConverter = new HtmlToPdfConverter();

        String outputFile = "/tmp/outputFile.pdf";
        try {
            htmlToPdfConverter.convert(htmlContent, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputFile;
    }
}
