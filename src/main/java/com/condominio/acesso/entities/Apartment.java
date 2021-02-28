package com.condominio.acesso.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(ApartmentId.class)
public class Apartment {

    @Id
    private Integer floor;

    @Id
    private Integer number;

    @OneToMany(mappedBy = "apartment")
    List<Resident> residents;
}
