package com.example.demo.login;

import com.example.demo.users.User;
import com.example.demo.users.UserRepository;
import com.example.demo.users.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public Boolean loginUser(LoginDTO loginDTO){

        User usuario = userRepository.findByEmailSenha(loginDTO.getEmail(),loginDTO.getSenha());
        if(usuario != null) return true;
        else return false;
    }
}
