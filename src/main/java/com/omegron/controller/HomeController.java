package com.omegron.controller;

import com.omegron.config.UserSession;
import com.omegron.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final UserSession userSession;
    private final UserService userService;

    public HomeController(UserSession userSession, UserService userService) {
        this.userSession = userSession;
        this.userService = userService;
    }

    @GetMapping("/")
    public String nonLoggedIndex() {
        if (userSession.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    @Transactional
    public String loggedInIndex(Model model) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

//        List<PaintingInfoDTO> myFavorites = userService.findFavourites(userSession.id())
//                .stream()
//                .map(PaintingInfoDTO::new)
//                .collect(Collectors.toList());
//
//        model.addAttribute("myFavoritesData", myFavorites);
//
        return "home";
    }
}