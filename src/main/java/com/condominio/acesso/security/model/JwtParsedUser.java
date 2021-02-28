package com.condominio.acesso.security.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class JwtParsedUser {
    public Long id;
    public String email;
    public String role;
}
