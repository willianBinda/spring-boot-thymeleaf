package com.example.demo.arquivos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArquivoRepository extends JpaRepository<Arquivo, String> {


    @Query("SELECT a FROM Arquivo a WHERE a.titulo LIKE %:query%")
    Page<Arquivo> searchArquivo(String query, Pageable pageable);
}
