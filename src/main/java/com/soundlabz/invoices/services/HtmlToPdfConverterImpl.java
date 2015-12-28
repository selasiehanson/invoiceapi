package com.soundlabz.invoices.services;

import com.soundlabz.invoices.components.HtmlToPdfConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.Map;

@Service
public class HtmlToPdfConverterImpl implements HtmlToPdfConverterService {

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public String convert(Map<String, Object> inputs) {
        final Context ctx = new Context();
        ctx.setVariable("toPdf", true);

        String style = getClass().getClassLoader().getResource("static/css/style.css").getFile();
        String bundleJs = getClass().getClassLoader().getResource("static/js/bundle.js").getFile();

        String stylePath = String.format("file://%s", style);
        String bundlePath = String.format("file://%s", bundleJs);
        ctx.setVariable("style", stylePath);
        ctx.setVariable("bundleJS", bundlePath);
        ctx.setVariable("data", inputs.get("data"));
        ctx.setVariable("logo", inputs.get("logo"));
        ctx.setVariable("invoice",inputs.get("invoice"));
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
