package com.example.form.service;

import com.example.form.domain.Item;
import com.example.form.domain.ItemAddForm;
import com.example.form.domain.User;
import com.example.form.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final UserService userService;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, UserService userService) {
        this.itemRepository = itemRepository;
        this.userService = userService;
    }

    /**public void addItem(ItemAddForm form, User username) {
        for (int i = 0; i < form.getAmount(); i++) {
            String inventoryCode = Long.toHexString(Double.doubleToLongBits(Math.random())).substring(10); //generate random string

            User user = userService.getUserByUsername(username);

            Item item = new Item(inventoryCode, form.getItemType(), username);
            itemRepository.save(item);
            System.out.println(itemRepository.findById(item.getId()));
        }
    }*/

    @Override
    public void addItem(ItemAddForm form) {

    }

    /**@Override
    public void addItem(ItemAddForm form) {
        for (int i = 0; i < form.getAmount(); i++) {
            String inventoryCode = Long.toHexString(Double.doubleToLongBits(Math.random())).substring(10); //generate random string
            Item item = new Item(inventoryCode, form.getItemType());
            //item.setUser(userService.);
            itemRepository.save(item);
            System.out.println(itemRepository.findById(item.getId()));
        }
    }*/

    @Override
    public void addItem(ItemAddForm form, String user) {
        for (int i = 0; i < form.getAmount(); i++) {
            String inventoryCode = Long.toHexString(Double.doubleToLongBits(Math.random())).substring(10); //generate random string
            Item item = new Item(inventoryCode, form.getItemType());
            item.setUser(userService.getUserByUsername(user));
            itemRepository.save(item);
            System.out.println(itemRepository.findById(item.getId()));
        }
    }

    @Override
    public Iterable<Item> getItems() {

        return itemRepository.findAll();
    }

    @Override
    public void deleteItemById(long id) {

        itemRepository.deleteById(id);
    }

    @Override
    public Optional<Item> getItemById(long id) {
        return itemRepository.findById(id);
    }

    @Override
    public Item assignItem(String username, long itemId) {
        //return Item
        //or orelse cak
        User user = userService.getUserByUsername(username);

        Optional<Item> item = getItemById(itemId);

        Set<Item> itemList = user.getItems();
        Item toBeAddedItem;

        if (item.isPresent()) {
            toBeAddedItem = item.get();
            itemList.add(toBeAddedItem);

            user.setItems(itemList);
            toBeAddedItem.setUser(user);
            return itemRepository.save(toBeAddedItem);
        } else {
             System.out.println("error");
             return new Item();
        }
    }
}
