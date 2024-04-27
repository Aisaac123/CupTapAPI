package com.upc.cuptap_restapi.Services.Shared.Instances;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.UpdateEntity;
import com.upc.cuptap_restapi.Services.Shared.Implements.UService;

public interface UServiceInstance<E extends UpdateEntity, ID extends Comparable<ID>>  {
    UService<E, ID> Modify();


}
