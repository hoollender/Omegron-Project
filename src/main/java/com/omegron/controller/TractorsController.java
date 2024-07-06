package com.omegron.controller;

import com.omegron.service.TractorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tractors")
public class TractorsController {
    private final TractorService tractorService;

    public TractorsController(TractorService tractorService) {
        this.tractorService = tractorService;
    }
    @GetMapping("/all")
    public String getAllTractors(Model model) {
        model.addAttribute("allTractors", tractorService.getAllTractorsSummary());
        return "all-tractors";
    }
}
