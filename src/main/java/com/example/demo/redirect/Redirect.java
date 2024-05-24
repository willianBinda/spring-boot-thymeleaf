package com.example.demo.redirect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Redirect {
    @GetMapping("/")
    public String redirect(){
        return "redirect:/home";
    }
}
