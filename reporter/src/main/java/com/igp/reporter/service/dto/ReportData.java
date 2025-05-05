package com.igp.reporter.service.dto;

import com.igp.reporter.dto.Item;
import java.util.List;

public class ReportData{
    
    private String title;

    private List<Item> items;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public List<Item> getItems() {
        return items;
    }
    public void setItems(List items) {
        this.items = items;
    }
}
