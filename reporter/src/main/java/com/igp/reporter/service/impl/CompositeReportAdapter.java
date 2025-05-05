package com.igp.reporter.service.impl;

import com.igp.reporter.service.ReportGeneratorPort;
import java.lang.UnsupportedOperationException;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.igp.reporter.service.dto.ReportData;
import org.springframework.context.annotation.Primary;
import com.igp.reporter.service.exception.ReportGenerationException;

@Service
@Primary
public class CompositeReportAdapter implements ReportGeneratorPort {
    
    private final Map<ReportFormat, ReportGeneratorPort> adapters;
    
    public CompositeReportAdapter(
            PdfReportAdapter pdfAdapter,
            HtmlReportAdapter htmlAdapter
            ) {
        
        this.adapters = Map.of(
            ReportFormat.PDF, pdfAdapter,
            //ReportFormat.EXCEL, excelAdapter,
            //ReportFormat.CSV, csvAdapter,
            ReportFormat.HTML, htmlAdapter
        );
    }
    
    @Override
    public byte[] generateReport(ReportData data, ReportFormat format) 
        throws ReportGenerationException {
        ReportGeneratorPort adapter = adapters.get(format);
        if (adapter == null) {
            throw new UnsupportedOperationException("Formato no soportado: " + format);
        }
        return adapter.generateReport(data, format);
    }
}