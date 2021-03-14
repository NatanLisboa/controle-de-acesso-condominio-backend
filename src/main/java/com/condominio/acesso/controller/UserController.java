package com.condominio.acesso.controller;

import com.condominio.acesso.dto.UserApartmentDTO;
import com.condominio.acesso.dto.UserDTO;
import com.condominio.acesso.entities.ApplicationUser;
import com.condominio.acesso.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
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
                        user.getId(),
                        user.getName(),
                        user.getCpf(),
                        user.getPhone(),
                        user.getEmail(),
                        user.getRole().toString()
                        )
                )
                .collect(Collectors.toList());
    }

    @GetMapping("/residents")
    public @ResponseBody List<UserApartmentDTO> findResidentsByCpf(@PathParam("cpf")String cpf){
        return this.userService.findResidentsByCPF(cpf)
                .stream()
                .map(UserApartmentDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/residables")
    public @ResponseBody List<UserDTO> getAllUsers(@PathParam("name")String name){
        return this.userService.findResidableUsers(name)
                .stream()
                .map(user -> new UserDTO(
                                user.getId(),
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
    public UserDTO createUser(@RequestBody @Valid UserDTO userDTO){
        return this.userService.createUser(new ApplicationUser(userDTO));
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
