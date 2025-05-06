package com.igp.reporter.service.impl;

import com.igp.reporter.service.ReportGeneratorPort;
import java.lang.UnsupportedOperationException;
import org.springframework.stereotype.Service;
import com.igp.reporter.service.exception.ReportGenerationException;
import org.thymeleaf.spring6.SpringTemplateEngine;
import com.igp.reporter.service.dto.ReportData;
import java.time.LocalDateTime;
import org.thymeleaf.context.Context;
import java.nio.charset.StandardCharsets;

@Service
public class HtmlReportAdapter implements ReportGeneratorPort {
    
    private final SpringTemplateEngine templateEngine;
    
    public HtmlReportAdapter(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
    
    @Override
    public byte[] generateReport(ReportData data, ReportFormat format) 
        throws ReportGenerationException {

        if (format != ReportFormat.HTML) {
            throw new UnsupportedOperationException("This adapter only supports HTML format");
        }
        
        Context context = new Context();
        context.setVariable("report", data);
        context.setVariable("title", data.getTitle());
        context.setVariable("items", data.getItems());
        context.setVariable("generationDate", LocalDateTime.now());
        
        String html = templateEngine.process("report-template", context);
        return html.getBytes(StandardCharsets.UTF_8);
    }
}