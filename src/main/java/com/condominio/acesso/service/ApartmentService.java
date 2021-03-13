package com.condominio.acesso.service;

import com.condominio.acesso.dto.ApartmentDTO;
import com.condominio.acesso.dto.ApartmentSaveDTO;
import com.condominio.acesso.entities.Apartment;
import com.condominio.acesso.entities.ApplicationUser;
import com.condominio.acesso.repository.ApartmentRepository;
import com.condominio.acesso.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final UserRepository userRepository;

    public ApartmentService(ApartmentRepository apartmentRepository, UserRepository userRepository) {
        this.apartmentRepository = apartmentRepository;
        this.userRepository = userRepository;
    }

    public List<Apartment> getAll() {
        return (List<Apartment>) this.apartmentRepository.findAllFetched();
    }

    public ApartmentDTO saveApartmentWithResidents(ApartmentSaveDTO apartmentDTO) {
        List<ApplicationUser> residents = this.findUsersByIdList(apartmentDTO.residents);
        Apartment apartment = new Apartment(null,apartmentDTO.getApartmentNumber(),residents,true);
        return new ApartmentDTO(apartmentRepository.save(apartment));
    }

    public ApartmentDTO updateApartmentWithResidents(ApartmentSaveDTO apartmentDTO, Long id) {
        Optional<Apartment> apartmentOptional = this.apartmentRepository.findById(id);
        return apartmentOptional.map(apartment -> {
            List<ApplicationUser> residents = this.findUsersByIdList(apartmentDTO.residents);
            apartment.setResidents(residents);
            apartment.setApartmentNumber(apartmentDTO.getApartmentNumber());
            return new ApartmentDTO(apartmentRepository.save(apartment));
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private List<ApplicationUser> findUsersByIdList(List<Long> ids){
        return ids
                .stream()
                .map(userRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public void deleteApartment(Long id) {
        Optional<Apartment> apartmentOptional = this.apartmentRepository.findById(id);
        apartmentOptional.ifPresent(apartment -> {
            apartment.setResidents(null);
            this.apartmentRepository.save(apartment);
        });
        this.apartmentRepository.deleteById(id);
    }
}
