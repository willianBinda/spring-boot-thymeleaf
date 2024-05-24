package com.example.demo.login;

import com.example.demo.users.UserDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute @Valid LoginDTO dto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            // Se houver erros, adicione-os ao modelo
            model.addAttribute("validationErrors", bindingResult.getAllErrors());
            // Retorne para a página do formulário para que o usuário possa corrigir os problemas
            return "login";
        }
        if ("willian@gmail.com".equals(dto.getEmail()) && "123123".equals(dto.getSenha())) {
//            System.out.println("primeira condição");
            return "redirect:/arquivos";
        } else {
//            System.out.println("segunda condição");
            model.addAttribute("validationErrors", bindingResult.getAllErrors());
//            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}
