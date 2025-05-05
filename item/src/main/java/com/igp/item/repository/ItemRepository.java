package com.igp.item.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igp.item.entities.Item;

public interface  ItemRepository extends JpaRepository<Item, Long>  {
    List<Item> findByIdOrden(Long idOrden);
}
