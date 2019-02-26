package com.example.form.controller;

import com.example.form.domain.User;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping(value = {"/", "/home"})
    public ModelAndView getHomePage(@AuthenticationPrincipal User user) {
    //public String getHomePage() {
     //   return "home";
    //}
        return new ModelAndView("home", "user", user);
    }
}
