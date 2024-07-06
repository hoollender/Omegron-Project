package com.omegron.controller;

import com.omegron.model.dto.AddTractorDTO;
import com.omegron.model.enums.EngineTypeEnum;
import com.omegron.model.enums.ManufacturersEnum;
import com.omegron.model.enums.TransmissionTypeEnum;
import com.omegron.service.TractorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tractors")
public class TractorController {

    private final TractorService tractorService;

    public TractorController(TractorService tractorService) {
        this.tractorService = tractorService;
    }


    @GetMapping("/add")
    public String addTractor(Model model) {
        if(!model.containsAttribute("addTractorDTO")) {
        model.addAttribute("addTractorDTO", AddTractorDTO.empty());
        }

        model.addAttribute("allEngineTypes", EngineTypeEnum.values());
        model.addAttribute("allTransmissionTypes", TransmissionTypeEnum.values());
        model.addAttribute("allManufacturers", ManufacturersEnum.values());
        return "tractor-add";
    }

    @PostMapping("/add")
    public String doAddTractor (@Valid AddTractorDTO addTractorDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addTractorDTO", addTractorDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addTractorDTO", bindingResult);
            return "redirect:/tractors/add";
        }
        long newTractorId = tractorService.addTractor(addTractorDTO);

        redirectAttributes.addFlashAttribute("successMessage", "Tractor added successfully!");
        redirectAttributes.addFlashAttribute("newTractorId", newTractorId);

        return "redirect:/tractors/add";
    }

    @DeleteMapping("/{id}")
    public String deleteTractor(@PathVariable("id") Long id) {

        tractorService.deleteTractor(id);

        return "redirect:/tractors/add";
    }
}
