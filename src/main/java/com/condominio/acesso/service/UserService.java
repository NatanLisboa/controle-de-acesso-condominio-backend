package com.condominio.acesso.service;

import com.condominio.acesso.dto.UserDTO;
import com.condominio.acesso.entities.ApplicationUser;
import com.condominio.acesso.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    public UserDTO createUser(ApplicationUser user) {
        return new UserDTO(userRepository.save(user));
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

    public List<ApplicationUser> findResidableUsers(String name) {
        return this.userRepository.findResidableUsers(name);
    }

    public List<ApplicationUser> findResidentsByCPF(String cpf) {
        return this.userRepository.findResidentsByCPF(cpf);
    }
}
