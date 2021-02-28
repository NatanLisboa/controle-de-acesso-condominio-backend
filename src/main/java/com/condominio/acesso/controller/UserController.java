package com.condominio.acesso.controller;

import com.condominio.acesso.dto.UserDTO;
import com.condominio.acesso.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAllUsers(){
        return ResponseEntity.ok(this.userService
                .findAllUser()
                .stream()
                .map(user -> new UserDTO(user.getName(),user.getEmail()))
                .collect(Collectors.toList()));

    }
}
