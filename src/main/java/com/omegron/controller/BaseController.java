package com.omegron.controller;

import com.omegron.model.user.OmegronUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;

//TODO: Fix welcome "username" on index page and all others.
public abstract class BaseController {

    @ModelAttribute("welcome_message")
    public String home(@AuthenticationPrincipal UserDetails userDetails,
                       Model model) {

        if (userDetails instanceof OmegronUserDetails omegronUserDetails) {
            model.addAttribute("welcome_message", ", " + omegronUserDetails.getFirstName());
        } else {
            model.addAttribute("welcome_message", "");
        }

        return "index";
    }
}