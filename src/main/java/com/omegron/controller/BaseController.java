package com.omegron.controller;

import com.omegron.model.user.OmegronUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
//TODO: Fix welcome "username" on index page and all others.
public abstract class BaseController {

    @ModelAttribute("welcomeMessage")
    public String home(@AuthenticationPrincipal UserDetails userDetails,
                       Model model) {

        if (userDetails instanceof OmegronUserDetails omegronUserDetails) {
            model.addAttribute("welcomeMessage", ", " + omegronUserDetails.getFirstName());
        } else {
            model.addAttribute("welcomeMessage", "");
        }

        return "index";
    }
}