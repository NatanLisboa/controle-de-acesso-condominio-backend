package com.condominio.acesso.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {

    @Id
    private Long id;

    @Size(min = 7,max = 7,message = "A placa deve ter 7 caracteres")
    private String registrationPlate;

    @ManyToMany
    private List<Resident> resident;

    @OneToOne
    private Visitor visitor;
}
