package com.example.demo.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDTO {
    private String id;
    @Email
    @NotBlank(message = "Email é obrigatório!")
    private String email;
    @NotBlank(message = "Senha é obrigatória!")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;
    @NotBlank(message = "Nivel é obrigatório!")
    private String nivel;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
