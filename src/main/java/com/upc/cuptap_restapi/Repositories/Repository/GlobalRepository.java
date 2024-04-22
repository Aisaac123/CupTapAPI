package com.upc.cuptap_restapi.Repositories.Repository;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;


/**
 * General Repository extended from {@link org.springframework.data.jpa.repository.JpaRepository}
 */
@NoRepositoryBean
public interface GlobalRepository<T extends Entity, ID extends Comparable<ID>> extends JpaRepository<T, ID> {

    @Query("SELECT e FROM #{#entityName} e WHERE e.fechaRegistro >= :fecha")
    Page<T> findAllByFechaRegistro(Pageable pageable, @Param("fecha") LocalDateTime fecha);

    @Query("SELECT e FROM #{#entityName} e WHERE e.fechaRegistro >= :fecha")
    List<T> findAllByFechaRegistro(@Param("fecha") LocalDateTime fecha);

    @Query("SELECT e FROM #{#entityName} e")

    List<T> findAllLazy();

}
