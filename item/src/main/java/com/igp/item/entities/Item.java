package com.igp.item.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idProducto;
    private Double cantidad;
    private Double precio;
    private Long idOrden;
    
    public Item() {
    }
    public Item(Double cantidad, Double precio) {
        this.cantidad = cantidad;
        this.precio = precio;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
    public Double getCantidad() {
        return cantidad;
    }
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public Long getIdOrden() {
        return idOrden;
    }
    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
    }
   
}
