package com.example.demo.users;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

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
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UserDTO dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        UserDTO userDTO = userService.criaUser(dto);


        return ResponseEntity.ok().body(Map.of("user",userDTO));
    }
}
