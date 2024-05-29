package com.example.demo.arquivos;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Empty;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ArquivoDTO {
    private String id;
    @NotBlank(message = "Adicione um título!")
    private String titulo;
    private MultipartFile[] files;
    private String filename;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;


}
