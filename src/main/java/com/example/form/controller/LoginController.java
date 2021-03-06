package com.example.form.controller;

//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class LoginController {

    //@PreAuthorize anotasyonuyla, getLoginPage methodunun sadece giriş yapmamış kullanıcılar için invoke edileceğini belirttik
    @PreAuthorize("isAnonymous()")
    @RequestMapping(value = "/login")
    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
        return new ModelAndView("login", "error", error);
    }
}
