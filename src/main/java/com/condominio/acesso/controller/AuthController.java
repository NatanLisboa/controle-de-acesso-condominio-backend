package com.condominio.acesso.controller;

import com.condominio.acesso.dto.LoginInfoDTO;
import com.condominio.acesso.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/first-access")
    public void doFirstAccess(@RequestBody LoginInfoDTO loginInfoDTO){
        this.authService.doFirstAccess(loginInfoDTO);
    }

    @PostMapping("/login")
    public AuthInfo doLogin(@RequestBody LoginInfoDTO loginInfoDTO){
        return this.authService.login(loginInfoDTO);
    }
}
