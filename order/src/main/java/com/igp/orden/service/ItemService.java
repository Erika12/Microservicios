package com.igp.orden.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.igp.orden.entities.Item;

@FeignClient(name = "item-service", url = "http://localhost:8083")
public interface ItemService {

    @GetMapping("/item/orden/{idOrden}")
    List<Item> obtenerItemPorIdOrden(@PathVariable Long idOrden);
}