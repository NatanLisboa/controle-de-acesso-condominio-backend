package com.condominio.acesso.repository;

import com.condominio.acesso.entities.Resident;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentRepository extends CrudRepository<Resident,Long> {
}
