package com.upc.cuptap_restapi.Services.General.Instances;

import com.upc.cuptap_restapi.Models.Interface.CreateEntity;
import com.upc.cuptap_restapi.Repository.Repositories.GlobalRepository;
import com.upc.cuptap_restapi.Services.General.GenericServices.CService;

public interface CInstance<E extends CreateEntity, ID extends Comparable<ID>> {
    CService<E, ID> Persist();
}
