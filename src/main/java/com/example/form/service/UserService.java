package com.example.form.service;

import com.example.form.domain.Item;
import com.example.form.domain.User;
import com.example.form.domain.UserAddForm;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    void addUser(UserAddForm form);

    Iterable<User> getUsers();

    void deleteItemById(long id);

    List<String> getUsernames();

    User getUserByUsername(String username);

    User getUserByUsername(User username);

    Optional<User> getUserById(long id);

    Map<String, List<Item>> numberOfItemsByType(long userId);
}
