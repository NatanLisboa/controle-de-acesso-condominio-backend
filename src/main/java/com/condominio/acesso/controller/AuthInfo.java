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
    private Role role;
}
