package com.soundlabz.invoices.components;

import java.io.*;
import java.util.LinkedList;

public class HtmlToPdfConverter {


    public void convert(String inputString, String outputFullPath) throws IOException {
        LinkedList<String> command = new LinkedList<String>();
        String file = getClass().getClassLoader().getResource("templates/invoice.html").getFile();

        String echoCommand = "echo";
        String pdfCommand = "wkhtmltopdf";

        try {
            Process p1 = new ProcessBuilder("echo", inputString).start();
            Process p2 = new ProcessBuilder(pdfCommand, "-", outputFullPath).start();

            Piper pipe = new Piper(p1.getInputStream(), p2.getOutputStream());
            new Thread(pipe).start();
            p2.waitFor();
            BufferedReader r = new BufferedReader(new InputStreamReader(p2.getInputStream()));
            String s = null;
            while ((s = r.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}