package com.zsmart.accountingProject.service.util;

import com.itextpdf.html2pdf.HtmlConverter;
import com.zsmart.accountingProject.bean.Facture;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class PdfUtil {

    public static Resource htmlToPdf(Facture facture, TemplateEngine templateEngine, String template) throws IOException {
        Context context = new Context();
        Map<String, Object> variables = new LinkedHashMap<>();
        variables.put("facture", facture);

        context.setVariables(variables);
        String html = templateEngine.process(template, context);

        try {
            try {
                FileUtils.cleanDirectory(new File("src\\main\\resources\\pdf\\"));
            } catch (IOException e) {
                ;
            }

            Long milli = new Date().getTime();
            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            String fileLocation = path.substring(0, path.length() - 1) + "src\\main\\resources\\pdf\\" + milli + "_temp.pdf";

            FileOutputStream outputStream = new FileOutputStream(fileLocation);
            HtmlConverter.convertToPdf(html, outputStream);
            Path path1 = Paths.get(path.substring(0, path.length() - 1) + "src\\main\\resources\\pdf\\");
            Path file = path1.resolve(milli + "_temp.pdf");
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                return null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
