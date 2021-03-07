package com.condominio.acesso.controller;

import com.condominio.acesso.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthInfo {
    private String accessToken;
    private String name;
    private String email;
    private Role role;
}
