package com.condominio.acesso.security.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public String token;

    public JwtAuthenticationToken(String token){
        super(null,null);
        this.token = token;
    }
}
