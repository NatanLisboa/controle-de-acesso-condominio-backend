package com.condominio.acesso.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Where(clause = "is_active=true")
@SQLDelete(sql = "UPDATE apartment SET is_active=false WHERE id=?")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String apartmentNumber;

    @OneToMany
    @JoinColumn(name = "apartment_id")
    private List<ApplicationUser> residents;

    private boolean isActive;
}
