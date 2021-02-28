package com.condominio.acesso.service;

import com.condominio.acesso.controller.AuthInfo;
import com.condominio.acesso.dto.LoginInfoDTO;
import com.condominio.acesso.entities.ApplicationUser;
import com.condominio.acesso.exceptions.AlreadyFirstAccessMade;
import com.condominio.acesso.exceptions.IncorrectPassword;
import com.condominio.acesso.exceptions.UserEmailNotFound;
import com.condominio.acesso.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JWTService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void doFirstAccess(LoginInfoDTO loginInfoDTO) {
        ApplicationUser applicationUser = this.userRepository.findByEmail(loginInfoDTO.email)
                .orElseThrow(() -> new UserEmailNotFound("Não foi encontrado usuário com este e-mail"));
        if(applicationUser.getPassword().equals("BLANK_PASSWORD")){
            applicationUser.setPassword(passwordEncoder.encode(loginInfoDTO.password));
        }else{
            throw new AlreadyFirstAccessMade();
        }
    }

    @Transactional
    public AuthInfo login(LoginInfoDTO loginInfoDTO) {
        ApplicationUser applicationUser = this.userRepository.findByEmail(loginInfoDTO.email)
                .orElseThrow(() -> new UserEmailNotFound("Não foi encontrado usuário com este e-mail"));
        if(passwordEncoder.matches(loginInfoDTO.password, applicationUser.getPassword())){
            return jwtService.buildAuthInfo(applicationUser);
        }else{
            throw new IncorrectPassword();
        }
    }

}
