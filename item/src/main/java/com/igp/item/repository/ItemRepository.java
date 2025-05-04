package com.igp.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igp.item.entities.Item;

public interface  ItemRepository extends JpaRepository<Item, Long>  {

}
