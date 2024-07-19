package com.omegron.controller;

import com.omegron.model.user.OmegronUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserProfileController {

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal OmegronUserDetails userDetails, Model model) {
        model.addAttribute("user", userDetails);
        return "profile";
    }
}