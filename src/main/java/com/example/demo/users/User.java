package com.example.demo.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    private String email;
    private String senha;
    private String nivel;

    @CreationTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;

}
