package com.igp.reporter.dto;

import java.io.Serializable;
import java.lang.String;


public class Item implements Serializable{

    private String id;
    private String name;
    private int value;
   
    public Item() {
    
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int  getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

}