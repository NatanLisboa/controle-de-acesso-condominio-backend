package com.condominio.acesso.service;

import com.condominio.acesso.entities.ApplicationUser;
import com.condominio.acesso.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Collection<ApplicationUser> findAllUser(){
        List<ApplicationUser> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
}
