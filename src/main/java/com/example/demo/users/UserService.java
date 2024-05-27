package com.example.demo.users;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserDTO criaUser(UserDTO userDTO){
        User user = modelMapper.map(userDTO,User.class);
        userRepository.save(user);
        return modelMapper.map(user,UserDTO.class);
    }

}
