package com.example.demo.arquivos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "arquivos")
@Data
public class Arquivo {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "data", columnDefinition="bytea")
    private byte[] data;
    private String titulo;
    private String filename;

    @CreationTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;

}
