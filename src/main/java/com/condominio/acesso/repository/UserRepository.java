package com.condominio.acesso.repository;

import com.condominio.acesso.entities.ApplicationUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByEmail(String email);

    @Query("FROM ApplicationUser u " +
            "LEFT JOIN u.apartment a " +
            "WHERE u.role='RESIDENT' " +
            "AND a IS NULL " +
            "AND u.name LIKE %:name%")
    List<ApplicationUser> findResidableUsers(@Param("name")String name);
}
