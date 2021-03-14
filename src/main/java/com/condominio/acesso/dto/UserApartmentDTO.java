package com.condominio.acesso.dto;

import com.condominio.acesso.entities.Apartment;
import com.condominio.acesso.entities.ApplicationUser;
import com.condominio.acesso.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserApartmentDTO extends UserDTO{
    public String apartmentNumber;

    public UserApartmentDTO(ApplicationUser user){
        super(user.getId(),user.getName(),user.getCpf(),user.getPhone(),user.getEmail(), user.getRole().toString());
        Apartment apartment = user.getApartment();
        if(apartment!=null){
            this.apartmentNumber = apartment.getApartmentNumber();
        }
    }
}
