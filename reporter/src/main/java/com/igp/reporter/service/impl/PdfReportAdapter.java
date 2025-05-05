package com.igp.reporter.service.impl;

import com.igp.reporter.service.ReportGeneratorPort;
import java.lang.UnsupportedOperationException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import org.springframework.stereotype.Service;
import com.igp.reporter.service.dto.ReportData;
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

@Service
//@Primary
public class PdfReportAdapter implements ReportGeneratorPort
{
    
    @Override
    public byte[] generateReport(ReportData data, ReportFormat format) 
        throws ReportGenerationException
    {
        if (format != ReportFormat.PDF) {
            throw new UnsupportedOperationException("This adapter only supports PDF format");
        }
        
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            
            document.open();
            document.add(new Paragraph("Reporte: " + data.getTitle()));
            document.add(new Paragraph("Generado el: " + LocalDate.now().toString()));
            
            // Tabla con datos
            PdfPTable table = new PdfPTable(3);
            addTableHeader(table);
            addDataRows(table, data);
            document.add(table);
            
            document.close();
            return baos.toByteArray();
        } catch (DocumentException e) {
            throw new ReportGenerationException("Error al generar PDF", e);
        } catch (IOException e) {
            throw new ReportGenerationException("Error al generar PDF", e);
        }
    }
    
    private void addTableHeader(PdfPTable table) {
        Stream.of("ID", "Nombre", "Valor")
            .forEach(columnTitle -> {
                PdfPCell header = new PdfPCell();
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setBorderWidth(2);
                header.setPhrase(new Phrase(columnTitle));
                table.addCell(header);
            });
    }
    
    private void addDataRows(PdfPTable table, ReportData data) {
        data.getItems().forEach(item -> {
            table.addCell(item.getId());
            table.addCell(item.getName());
            table.addCell(String.valueOf(item.getValue()));
        });
    }
}