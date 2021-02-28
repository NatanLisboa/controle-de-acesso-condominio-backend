package com.condominio.acesso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginInfoDTO {

    @Email
    public String email;

    @Size(min = 6)
    public String password;
}
