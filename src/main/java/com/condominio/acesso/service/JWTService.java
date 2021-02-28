package com.condominio.acesso.service;

import com.condominio.acesso.controller.AuthInfo;
import com.condominio.acesso.entities.ApplicationUser;
import com.condominio.acesso.security.model.JwtParsedUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

@Service
public class JWTService {

    @Value( "${jwt-secret}" )
    String jwtSecret;

    public AuthInfo buildAuthInfo(ApplicationUser applicationUser) {

        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", applicationUser.getRole());
        claims.put("email",applicationUser.getEmail());

        LocalDate now = LocalDate.now();
        LocalDate plusHour = now.plus(10, ChronoUnit.YEARS);
        Date expireDate = Date.from(plusHour.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        String jws = Jwts.builder()
                .setSubject(applicationUser.getId().toString())
                .signWith(secretKey)
                .setExpiration(expireDate)
                .addClaims(claims)
                .compact();

        return new AuthInfo(jws, applicationUser.getRole());
    }

    public Optional<JwtParsedUser> parseToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        JwtParsedUser user = null;
        try{
            Claims body = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            user = new JwtParsedUser(
                    Long.valueOf(body.getSubject()),
                    body.get("email",String.class),
                    body.get("role",String.class)
            );
        }catch (JwtException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }
}
