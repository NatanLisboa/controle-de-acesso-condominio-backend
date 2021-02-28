package com.condominio.acesso.security.jwt;


import com.condominio.acesso.exceptions.InvalidJWTException;
import com.condominio.acesso.security.model.AuthenticatedUser;
import com.condominio.acesso.security.model.JwtParsedUser;
import com.condominio.acesso.service.JWTService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private final JWTService jwtService;

    public JwtAuthenticationProvider(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String token = jwtAuthenticationToken.token;

        JwtParsedUser parsedUser = this.jwtService
                .parseToken(token)
                .orElseThrow(() -> new InvalidJWTException("JWT Token is not valid"));

        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(parsedUser.role);

        return new AuthenticatedUser(parsedUser.id,parsedUser.email,token,authorities);
    }
}
