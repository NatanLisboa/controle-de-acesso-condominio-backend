package com.condominio.acesso.dto;

import com.condominio.acesso.entities.Apartment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentDTO {
    public Long id;
    public String apartmentNumber;
    public List<UserDTO> residents;

    public ApartmentDTO(Apartment apartment) {
        this.id = apartment.getId();
        this.apartmentNumber = apartment.getApartmentNumber();
        this.residents = apartment.getResidents().stream().map(UserDTO::new).collect(Collectors.toList());
    }
}
