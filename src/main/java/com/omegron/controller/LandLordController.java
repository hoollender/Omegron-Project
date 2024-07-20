package com.omegron.controller;

import com.omegron.model.dto.AddLandLordDTO;
import com.omegron.model.dto.LandLordDTO;
import com.omegron.service.LandLordService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/landlords")
public class LandLordController {

    private final LandLordService landLordService;

    public LandLordController(LandLordService landLordService) {
        this.landLordService = landLordService;
    }

    @GetMapping("/all")
    public String getAllLandlords(Model model) {
        model.addAttribute("allLandLords", landLordService.getAllLandLordsSummary());
        return "all-landlords";
    }

    @GetMapping("/details/{id}")
    public String landLordDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("landLordDetails", landLordService.getLandLordDetails(id));
        return "landlord-details";
    }

    //ADDING A LANDLORD

    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addLandLord(Model model) {
        if (!model.containsAttribute("addLandLordDTO")) {
            model.addAttribute("addLandLordDTO", AddLandLordDTO.empty());
        }

        return "landlord-add";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String doAddLandLord(@Valid AddLandLordDTO addLandLordDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addLandLordDTO", addLandLordDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addLandLordDTO", bindingResult);
            return "redirect:/landlords/add";
        }
        landLordService.addLandLord(addLandLordDTO);

        //TODO: Fix to show successfully added message.
//        redirectAttributes.addFlashAttribute("successMessage", "LandLord added successfully!");
//        redirectAttributes.addFlashAttribute("newLandLordId", newLandLordId);

        return "redirect:/landlords/all";
    }
// UPDATING A LANDLORD

    @GetMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showUpdateForm(@PathVariable Long id, Model model) {

        LandLordDTO landLordDetails = landLordService.findById(id);
        model.addAttribute("landLordDetails", landLordDetails);
        return "landlord-update";
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateLandLord(@PathVariable Long id, @ModelAttribute("landLordDetails") LandLordDTO landLordDTO) {
        landLordService.updateLandLord(id, landLordDTO);
        return "redirect:/landlords/details/" + id; // Redirect to the details page and shows the same LandLord again with updated stats.
    }

//DELETING A LANDLORD

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteLandLord(@PathVariable("id") Long id) {

        landLordService.deleteLandLord(id);

        return "redirect:/landlords/all";
    }
}
