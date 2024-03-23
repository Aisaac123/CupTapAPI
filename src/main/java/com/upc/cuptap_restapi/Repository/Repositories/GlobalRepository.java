package com.upc.cuptap_restapi.Repository.Repositories;

import com.upc.cuptap_restapi.Models.Interface.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GlobalRepository<T extends Entity, ID extends Comparable<ID>> extends JpaRepository<T, ID> {

}
