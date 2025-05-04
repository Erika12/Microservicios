package com.igp.orden.service;
import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;


import com.igp.orden.entities.Orden;

import com.igp.orden.repository.OrdenRepository;


@Service
public class OrdenService {

    private final OrdenRepository repository;
    
 
    public OrdenService(OrdenRepository repository) {
        this.repository = repository;
    }

    public List<Orden> listar() {
        return repository.findAll();
    }

    public Orden guardar(Orden orden) {
        return repository.save(orden);
    }

    public Orden buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Orden actualizar(Long id, Orden nuevo) {
        Optional<Orden> encontrado = repository.findById(id);
        if (encontrado.isPresent()) {
            Orden orden = encontrado.get();
            orden.setFechaOrden(nuevo.getFechaOrden());
            orden.setTotal(nuevo.getTotal());
            orden.setIdCustomer(nuevo.getIdCustomer());
            return repository.save(orden);
        }
        return null;
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

   
    public List<Orden> obtenerOrdenesPorCliente(Long idCustomer) {
        return repository.findByIdCustomer(idCustomer);
    }

}
