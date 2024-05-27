package com.example.demo.users;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users/new")
    public String usuarios(Model model){
//        model.addAttribute("user",new UserDTO());
        model.addAttribute("logged", true);

        return "users/new";
    }

    @PostMapping("users/new")
    public String cadastrar(@ModelAttribute @Valid UserDTO dto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            // Se houver erros, adicione-os ao modelo
            model.addAttribute("validationErrors", bindingResult.getAllErrors());
            //model.addAttribute("logged", true);

            // Retorne para a página do formulário para que o usuário possa corrigir os problemas
            return "users/new";
        }
        UserDTO userDTO = userService.criaUser(dto);
        model.addAttribute("userSaved",userDTO);
        //model.addAttribute("logged", true);

        return "users/new";
    }
}
