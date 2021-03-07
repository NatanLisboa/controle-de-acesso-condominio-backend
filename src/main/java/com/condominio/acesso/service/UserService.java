package com.condominio.acesso.service;

import com.condominio.acesso.dto.UserDTO;
import com.condominio.acesso.entities.ApplicationUser;
import com.condominio.acesso.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly=true)
    public Collection<ApplicationUser> findAllUser(){
        List<ApplicationUser> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Transactional
    public void createUser(ApplicationUser user) {
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(UserDTO userDTO, Long userId) {
        Optional<ApplicationUser> optionalUserToUpdate = userRepository.findById(userId);
        optionalUserToUpdate.ifPresent(user -> {
            user.updateUser(userDTO);
        });
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
