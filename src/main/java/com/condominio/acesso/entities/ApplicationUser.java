package com.condominio.acesso.entities;

import com.condominio.acesso.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE application_user SET is_active = false WHERE id=?")
@Where(clause = "is_active=true")
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @CPF
    private String cpf;
    @Size(max = 11)
    private String phone;
    @Email
    private String email;

    @Size(min = 6)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Apartment apartment;

    private boolean isActive;

    public ApplicationUser(UserDTO userDTO) {
        this.name = userDTO.getName();
        this.cpf = userDTO.getCpf();
        this.phone = userDTO.getPhone();
        this.email = userDTO.getEmail();
        this.password = "BLANK_PASSWORD";
        this.role = Role.fromString(userDTO.getRole());
    }

    public void updateUser(UserDTO userDTO){
        this.setName(userDTO.getName());
        this.setCpf(userDTO.getCpf());
        this.setPhone(userDTO.getPhone());
        this.setEmail(userDTO.getEmail());
        this.setRole(Role.fromString(userDTO.getRole()));
    }

    @PrePersist
    public void prePersist(){
        this.isActive = true;
    }
}
