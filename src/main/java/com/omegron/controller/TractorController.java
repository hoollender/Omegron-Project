package com.omegron.controller;

import com.omegron.model.dto.AddTractorDTO;
import com.omegron.model.dto.TractorDetailsDTO;
import com.omegron.model.enums.EngineTypeEnum;
import com.omegron.model.enums.ManufacturersEnum;
import com.omegron.model.enums.TransmissionTypeEnum;
import com.omegron.service.TractorService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
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

//ADDING A TRACTOR

    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addTractor(Model model) {
        if (!model.containsAttribute("addTractorDTO")) {
            model.addAttribute("addTractorDTO", AddTractorDTO.empty());
        }

        model.addAttribute("allEngineTypes", EngineTypeEnum.values());
        model.addAttribute("allTransmissionTypes", TransmissionTypeEnum.values());
        model.addAttribute("allManufacturers", ManufacturersEnum.values());
        return "tractor-add";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String doAddTractor(@Valid AddTractorDTO addTractorDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addTractorDTO", addTractorDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addTractorDTO", bindingResult);
            return "redirect:/tractors/add";
        }
        tractorService.addTractor(addTractorDTO);

        //TODO: Fix to show successfully added message.
//        redirectAttributes.addFlashAttribute("successMessage", "Tractor added successfully!");
//        redirectAttributes.addFlashAttribute("newTractorId", newTractorId);

        return "redirect:/tractors/all";
    }
// UPDATING A TRACTOR

    @GetMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showUpdateForm(@PathVariable Long id, Model model) {

        TractorDetailsDTO tractorDetails = tractorService.findById(id);
        model.addAttribute("tractorDetails", tractorDetails);
        model.addAttribute("allEngineTypes", EngineTypeEnum.values());
        model.addAttribute("allTransmissionTypes", TransmissionTypeEnum.values());
        model.addAttribute("allManufacturers", ManufacturersEnum.values());
        return "tractor-update";
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateTractor(@PathVariable Long id, @ModelAttribute("tractorDetails") TractorDetailsDTO tractorDetailsDTO) {
        tractorService.updateTractor(id, tractorDetailsDTO);
        return "redirect:/tractors/details/" + id; // Redirect to the details page and shows the same tractor again with updated stats.
    }


//DELETING A TRACTOR

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteTractor(@PathVariable("id") Long id) {

        tractorService.deleteTractor(id);

        return "redirect:/tractors/all";
    }
}
