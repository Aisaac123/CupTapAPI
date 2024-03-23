package com.upc.cuptap_restapi.Services.General.Instances;

import com.upc.cuptap_restapi.Models.Interface.CrudEntity;
import com.upc.cuptap_restapi.Repository.Repositories.GlobalRepository;
import com.upc.cuptap_restapi.Services.General.GenericServices.CService;
import com.upc.cuptap_restapi.Services.General.GenericServices.Intefaces.Services;
import org.springframework.stereotype.Service;


public interface CRUDInstance<E extends CrudEntity, ID extends Comparable<ID>> extends Services, RInstance<E, ID>, UInstance<E, ID>, DInstance<E, ID>, CInstance<E, ID> {


}
