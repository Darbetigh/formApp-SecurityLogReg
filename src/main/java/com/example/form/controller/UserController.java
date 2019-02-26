package com.example.form.controller;

import com.example.form.domain.UserAddForm;
import com.example.form.domain.validator.RegisterValidator;
import com.example.form.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;
    private final RegisterValidator registerValidator;


    @Autowired
    public UserController(UserService userService, RegisterValidator registerValidator) {
        this.userService = userService;
        this.registerValidator = registerValidator;
    }


    @RequestMapping("/register")
    public ModelAndView getRegisterPage() {

        return new ModelAndView("register", "userForm", new UserAddForm());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleRegisterForm(@Valid @ModelAttribute("userForm") UserAddForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "register";
        userService.addUser(form);
        return "redirect:/users";
    }

    @RequestMapping("/users")
    public ModelAndView getUsersPage() {

        return new ModelAndView("users", "users", userService.getUsers());
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public String handleItemDelete(@PathVariable Long id) {
        userService.deleteItemById(id);
        return "redirect:/users";
    }
}
