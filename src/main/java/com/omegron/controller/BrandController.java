package com.omegron.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BrandController {

    @GetMapping("/brands/all")
    public String brands(Model model) {
        return "brands";
    }
}
