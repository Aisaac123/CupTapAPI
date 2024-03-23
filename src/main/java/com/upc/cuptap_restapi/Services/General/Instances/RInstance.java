package com.upc.cuptap_restapi.Services.General.Instances;

import com.upc.cuptap_restapi.Models.Interface.ReadEntity;
import com.upc.cuptap_restapi.Repository.Repositories.GlobalRepository;
import com.upc.cuptap_restapi.Services.General.GenericServices.RService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public interface RInstance<E extends ReadEntity, ID extends Comparable<ID>> {
     RService<E, ID> Read();
}
