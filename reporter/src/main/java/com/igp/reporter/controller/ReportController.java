package com.igp.reporter.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.igp.reporter.service.dto.ReportData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpHeaders;

import com.igp.reporter.service.ReportGeneratorPort;
import com.igp.reporter.service.ReportGeneratorPort.ReportFormat;
import org.springframework.http.MediaType;
import com.igp.reporter.service.exception.ReportGenerationException;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    
    private final ReportGeneratorPort reportGenerator;
    
    public ReportController(ReportGeneratorPort reportGenerator) {
        this.reportGenerator = reportGenerator;
    }
    
    @GetMapping
    public ResponseEntity<byte[]> generateReport(
            @RequestParam ReportFormat format,
            @RequestBody ReportData data) 
            throws ReportGenerationException{
        
        byte[] reportContent = reportGenerator.generateReport(data, format);
        
        String contentType = switch(format) {
            case PDF -> "application/pdf";
            case HTML -> "text/html";
        };
        
        String fileName = "reporte." + format.name().toLowerCase();
        
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(reportContent);
    }
}