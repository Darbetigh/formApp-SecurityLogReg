package com.example.form.service;

import com.example.form.domain.Item;
import com.example.form.domain.ItemAddForm;
import com.example.form.domain.User;

import java.util.Optional;

public interface ItemService {

    void addItem(ItemAddForm form);

    void addItem(ItemAddForm form, String user);

    Iterable<Item> getItems();

    void deleteItemById(long id);

    Optional<Item> getItemById(long id);

    Item assignItem(String username, long itemId);

    //void editItem(ItemAddForm form);
}
