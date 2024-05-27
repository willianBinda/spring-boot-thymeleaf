package com.example.demo.arquivos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArquivoDTO2 {
    private String id;
    @NotBlank(message = "Adicione um título!")
    private String titulo;
    private byte[] data;
    private String filename;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;


}
