package com.example.demo.login;

import com.example.demo.users.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
public class LoginController {

    private final LoginService loginService;


    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("logged", false);

        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginSubmit(@RequestBody @Valid LoginDTO dto, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {

            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        Boolean isUser = loginService.loginUser(dto);

        if (isUser) {
            return ResponseEntity.ok().body(Map.of("redirectUrl","/arquivos"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado!");
        }
    }
}
