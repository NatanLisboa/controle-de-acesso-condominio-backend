package com.condominio.acesso.repository;

import com.condominio.acesso.entities.Visitor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends CrudRepository<Visitor,Long> {
}
