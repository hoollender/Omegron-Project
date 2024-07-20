package com.omegron.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-error")
    public ModelAndView loginError() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("showErrorMessage", true);
        return modelAndView;
    }
}
