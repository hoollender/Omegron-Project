package com.omegron.controller;

import com.omegron.service.TractorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tractors")
public class TractorDetailController extends BaseController {

    private final TractorService tractorService;

    public TractorDetailController(TractorService tractorService) {
        this.tractorService = tractorService;
    }

    @GetMapping("/{id}")
    public String tractorDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("tractorDetails", tractorService.getTractorDetails(id));
        return "details";
    }
}
