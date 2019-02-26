package com.example.form.repository;

import com.example.form.domain.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {

    Item findByInventoryCode(String inventoryCode);
}
