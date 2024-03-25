package com.upc.cuptap_restapi.Repositories.Repository;

import com.upc.cuptap_restapi.Models.Interfaces.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GlobalRepository<T extends Entity, ID extends Comparable<ID>> extends JpaRepository<T, ID> {


}
