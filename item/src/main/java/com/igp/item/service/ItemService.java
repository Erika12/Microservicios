package com.igp.item.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.igp.item.entities.Item;
import com.igp.item.repository.ItemRepository;

@Service
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> listar() {
        return repository.findAll();
    }

    public Item guardar(Item item) {
        return repository.save(item);
    }

    public Item buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Item actualizar(Long id, Item nuevo) {
        Optional<Item> encontrado = repository.findById(id);
        if (encontrado.isPresent()) {
            Item item = encontrado.get();
            item.setIdProducto(nuevo.getIdProducto());
            item.setCantidad(nuevo.getCantidad());
            item.setPrecio(nuevo.getPrecio());
            item.setIdOrden(nuevo.getIdOrden());
            return repository.save(item);
        }
        return null;
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<Item> obtenerItemPorIdOrden(Long idOrden) {
        return repository.findByIdOrden(idOrden);
    }
}
