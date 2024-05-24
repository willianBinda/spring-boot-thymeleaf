package com.example.demo.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    @NotBlank(message = "Email é obrigatório!")
    @Email(message = "Email inválido!")
    private String email;

    @NotBlank(message = "Senha é obrigatória!")
    @Size(min = 6,message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;
}
