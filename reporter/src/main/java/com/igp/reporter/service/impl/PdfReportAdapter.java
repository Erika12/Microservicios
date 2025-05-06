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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.igp.reporter.dto.Item;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

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
            document.add(new Paragraph(""));
            
            // Tabla con datos
            PdfPTable table = new PdfPTable(3);
            addTableHeader(table);
            addDataRows(table, data);
            document.add(table);
            
            document.close();
            return baos.toByteArray();
        } catch (DocumentException e) {
            throw new ReportGenerationException("Error al generar PDF", e);
        } catch (IOException ex) {
            throw new ReportGenerationException("Error al generar PDF", ex);
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
    
    private void addDataRows(PdfPTable table, ReportData data) 
        throws IOException{
        System.out.println("Items: "+ data.getItems().toString());
        try{
            //List<Item> itemList = PdfReportAdapter.jsonArrayToList(data.getItems().toString(), Item.class);
            ObjectMapper objectMapper = new ObjectMapper();
            
            CollectionType listType = 
                        objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Item.class);
            
            List<Item> itemList = objectMapper.readValue(data.getItems().toString(), listType);

            System.out.println(itemList);
            Iterator<Item> itemsIterator = itemList.iterator();
            while(itemsIterator.hasNext()) {
                Item item =  itemsIterator.next();
                /*System.out.println(item);*/
                table.addCell(item.getId());
                table.addCell(item.getName());
                table.addCell(String.valueOf(item.getValue()));
            }
        }catch(IOException ex){
            System.out.println("Problema en la deserializacion");
            
        }
    }

    /*public static <T> List<T> jsonArrayToList(String json, Class<T> elementClass) 
        throws JsonProcessingException {
            System.out.println(json);
            System.out.println(elementClass);

        ObjectMapper objectMapper = new ObjectMapper();
        List<Item> itemList = objectMapper.readValue(json, new TypeReference<List<Item>>() {});

       
        /*CollectionType listType = 
            objectMapper.getTypeFactory().constructCollectionType(List.class, elementClass);
        
        return objectMapper.readValue(json, listType);*/

     /*   return itemList;
    }*/
}