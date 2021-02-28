package com.condominio.acesso.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncodeProvider {

    @Bean
    public PasswordEncoder encoder(){
        return new Argon2PasswordEncoder();
    }
}
