package com.example.form.controller;

import com.example.form.domain.ItemAddForm;
import com.example.form.domain.ItemAssignForm;
import com.example.form.service.ItemService;
import com.example.form.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Controller
public class ItemController {

    private final ItemService itemService;
    private final UserService userService;

    @Autowired
    public ItemController(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }
    @RequestMapping("/items/add")
    public ModelAndView itemAddPage() {

        return new ModelAndView("addItem", "itemForm", new ItemAddForm());
    }

    @RequestMapping(value = "/items", method = RequestMethod.POST)
    public String handleItemAdd(@Valid @ModelAttribute("itemForm") ItemAddForm form, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors())
            return "addItem";
        itemService.addItem(form, principal.getName());
        //itemService.addItem(form);

        return "redirect:/items";
    }

    /**@RequestMapping("/items")
    public ModelAndView getItemsPage() {
        return new ModelAndView("items", "items", itemService.getItems());
    }*/

    @RequestMapping("/items")
    public ModelAndView getItemsPage() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("items", itemService.getItems());
        model.put("usernames", userService.getUsernames());
        model.put("assignForm", new ItemAssignForm());
        return new ModelAndView("items", model);
    }

    @RequestMapping(value = "/items/{id}", method = RequestMethod.PUT)
    public String handleItemAssign(@ModelAttribute("user") ItemAssignForm form, @PathVariable("id") long id) {
        itemService.assignItem(form.getUsername(), id);
        return "redirect:/items";
    }

    @RequestMapping(value = "/items/{id}", method = RequestMethod.DELETE)
    public String handleItemDelete(@PathVariable Long id) {
        itemService.deleteItemById(id);
        return "redirect:/items";
    }

    @RequestMapping(value = "/users/{id}/items")
    public ModelAndView getUserPage(@PathVariable Long id) {
        if (null == userService.getUserById(id))
            throw new NoSuchElementException("User with id:" + id + " not found");
        else
            return new ModelAndView("userItems" ,"items", userService.numberOfItemsByType(id));
    }
}
