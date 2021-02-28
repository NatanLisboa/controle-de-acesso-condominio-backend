package com.condominio.acesso.security.exception;

import org.springframework.security.core.AuthenticationException;

public class MissingAuthenticationHeader extends AuthenticationException {
    public MissingAuthenticationHeader(String s) {
        super(s);
    }
}
