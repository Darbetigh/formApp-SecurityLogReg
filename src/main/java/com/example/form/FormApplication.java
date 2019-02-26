package com.example.form;

import com.example.form.domain.Item;
import com.example.form.domain.User;
import com.example.form.repository.ItemRepository;
import com.example.form.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class FormApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    public static void main(String[] args) {

        SpringApplication.run(FormApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Item item1 = new Item("123S", "Bilgisayar");
        Item item2 = new Item("358G", "Bilgisayar");
        Item item3 =  new Item("158A", "Bilgisayar");
        Item item4 = new Item("935C", "Telefon");

        User user1 = new User("sedo", "123456", "ahmet", "kara");
        User user2 = new User("hool", "hoo123", "mehmet", "ak");

        Set set1 = new HashSet<Item>();
        set1.add(item1);
        set1.add(item3);
        set1.add(item4);

        Set set2 = new HashSet<Item>();
        set2.add(item2);

        user1.setItems(set1);
        user2.setItems(set2);

        item1.setUser(user1);
        item3.setUser(user1);
        item4.setUser(user1);

        item2.setUser(user2);

        userRepository.save(user1);
        userRepository.save(user2);

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);
    }
}
