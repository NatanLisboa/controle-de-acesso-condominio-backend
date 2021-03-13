package com.condominio.acesso.controller;

import com.condominio.acesso.dto.ApartmentDTO;
import com.condominio.acesso.dto.ApartmentSaveDTO;
import com.condominio.acesso.service.ApartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/apartment")
public class ApartmentController {

    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping
    public @ResponseBody List<ApartmentDTO> getAllApartments(){
        return this.apartmentService.getAll()
                .stream()
                .map(ApartmentDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ApartmentDTO saveApartment(@RequestBody ApartmentSaveDTO apartmentDTO){
        return this.apartmentService.saveApartmentWithResidents(apartmentDTO);
    }

    @PutMapping("/{id}")
    public ApartmentDTO updateApartment(@RequestBody ApartmentSaveDTO apartmentDTO,@PathVariable Long id){
        return this.apartmentService.updateApartmentWithResidents(apartmentDTO,id);
    }

    @DeleteMapping("/{id}")
    public void deleteApartment(@PathVariable Long id){
        this.apartmentService.deleteApartment(id);
    }
}
