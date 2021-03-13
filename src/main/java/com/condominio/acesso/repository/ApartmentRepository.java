package com.condominio.acesso.repository;

import com.condominio.acesso.entities.Apartment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends CrudRepository<Apartment, Long> {
    @Query("FROM Apartment a JOIN FETCH a.residents r")
    List<Apartment> findAllFetched();
}
