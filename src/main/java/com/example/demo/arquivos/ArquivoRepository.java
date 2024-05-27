package com.example.demo.arquivos;

import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArquivoRepository extends JpaRepository<Arquivo, String> {
}
