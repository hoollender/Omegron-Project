package com.omegron.controller;


import com.omegron.model.user.OmegronUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    @ModelAttribute("welcome_user")
    public String addWelcomeUserAttribute(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails instanceof OmegronUserDetails omegronUserDetails) {
            return omegronUserDetails.getFirstName() + " " + omegronUserDetails.getLastName();
        }
        return "User";
    }

    @GetMapping("/")
    public String index(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails instanceof OmegronUserDetails) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
