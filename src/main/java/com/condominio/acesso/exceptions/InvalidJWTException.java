package com.condominio.acesso.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidJWTException extends AuthenticationException {
    public InvalidJWTException(String message) {
        super(message);
    }
}
