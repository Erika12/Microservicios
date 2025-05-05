package com.igp.orden.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.igp.orden.entities.Orden;
import com.igp.orden.service.OrdenService;



@RestController
@RequestMapping("/orden")
public class OrdenController {
    
    private final OrdenService service;
    
    public OrdenController(OrdenService service) {
        this.service = service;
    }

    @GetMapping
    public List<Orden> listar() {
        return service.listar();
    }

    @PostMapping
    public Orden crear(@RequestBody Orden orden) {
        return service.guardar(orden);
    }

    @GetMapping("/{id}")
    public Orden obtener(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Orden actualizar(@PathVariable Long id, @RequestBody Orden orden) {
        return service.actualizar(id, orden);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @PutMapping("/update/{id}")
    public Orden actualizarOrdenById(@PathVariable Long id, @RequestBody Orden orden) {
        return service.actualizarOrdenById(id, orden);
    }
}
