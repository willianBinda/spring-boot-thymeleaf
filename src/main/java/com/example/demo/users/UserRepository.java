package com.example.demo.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,String> {


    @Query("SELECT u FROM User u WHERE u.email = :email AND u.senha = :senha")
    User findByEmailSenha(@Param("email") String email, @Param("senha") String senha);

}
