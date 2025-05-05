package com.igp.reporter.service;
import java.util.List;
import java.util.Optional;
import com.igp.reporter.service.dto.ReportData;
import java.lang.Exception;

import org.springframework.stereotype.Service;
import com.igp.reporter.service.exception.ReportGenerationException;

public interface ReportGeneratorPort {
    byte[] generateReport(ReportData data, ReportFormat format)
        throws ReportGenerationException;
    
    enum ReportFormat {
        PDF, HTML
    }
}

