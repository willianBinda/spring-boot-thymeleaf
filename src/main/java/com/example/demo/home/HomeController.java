package com.example.demo.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class HomeController {
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("message", "Welcome to the home page!");
        model.addAttribute("logged", false);
        return "home";
    }
}
