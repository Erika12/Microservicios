package com.igp.reporter.service.impl;

import com.igp.reporter.service.ReportGeneratorPort;
import java.lang.UnsupportedOperationException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;
import java.time.LocalDate;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Phrase;
import java.util.stream.Stream;
import com.igp.reporter.service.exception.ReportGenerationException;
import java.lang.Exception;
import java.io.IOException;
import org.springframework.context.annotation.Primary;
import org.thymeleaf.context.Context;
import com.igp.reporter.service.dto.ReportData;
import java.time.LocalDateTime;
import org.thymeleaf.spring6.SpringTemplateEngine;
import com.itextpdf.html2pdf.HtmlConverter;


@Service
//@Primary
public class PdfReportAdapterThy implements ReportGeneratorPort
{

    private final SpringTemplateEngine templateEngine;

    public PdfReportAdapterThy(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
    
    @Override
    public byte[] generateReport(ReportData data, ReportFormat format) 
        throws ReportGenerationException
    {
        if (format != ReportFormat.PDF) {
            throw new UnsupportedOperationException("This adapter only supports PDF format");
        }
        
        Context context = new Context();
        context.setVariable("report", data);
        context.setVariable("title", data.getTitle());
        context.setVariable("items", data.getItems());
        context.setVariable("generationDate", LocalDateTime.now());
        
        String html = templateEngine.process("report-template", context);

        byte[] pdf = generatePdfFromHtml(html);
        String fileName = data.getTitle();

        return pdf;
    }

    private byte[] generatePdfFromHtml(String html){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(html, output);
        return output.toByteArray();
       }
}   
