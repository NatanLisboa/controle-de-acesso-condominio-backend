package com.condominio.acesso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentSaveDTO {
    public String apartmentNumber;
    public List<Long> residents;
}
