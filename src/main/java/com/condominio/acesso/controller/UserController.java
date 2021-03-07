package com.condominio.acesso.controller;

import com.condominio.acesso.dto.UserDTO;
import com.condominio.acesso.entities.ApplicationUser;
import com.condominio.acesso.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public @ResponseBody List<UserDTO> findAllUsers(){
        return this.userService
                .findAllUser()
                .stream()
                .map(user -> new UserDTO(
                        user.getName(),
                        user.getCpf(),
                        user.getPhone(),
                        user.getEmail(),
                        user.getRole().toString()
                        )
                )
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid UserDTO userDTO){
        this.userService.createUser(new ApplicationUser(userDTO));
    }

    @PutMapping("/{id}")
    public void updateUser(@RequestBody UserDTO userDTO,@PathVariable Long id){
        this.userService.updateUser(userDTO,id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        this.userService.deleteUser(id);
    }
}
