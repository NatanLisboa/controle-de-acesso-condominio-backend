package com.condominio.acesso.dto;

import com.condominio.acesso.entities.ApplicationUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    public Long id;
    public String name;
    @CPF
    public String cpf;
    @Size(max = 11,min = 11)
    public String phone;
    @Email
    public String email;
    public String role;

    public UserDTO(ApplicationUser applicationUser){
        this.id = applicationUser.getId();
        this.name = applicationUser.getName();
        this.cpf = applicationUser.getCpf();
        this.phone = applicationUser.getPhone();
        this.email = applicationUser.getEmail();
        this.role = applicationUser.getRole().name();
    }
}
