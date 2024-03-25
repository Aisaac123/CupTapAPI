package com.upc.cuptap_restapi.Services.DataAccess.DASIntances;

import com.upc.cuptap_restapi.Models.Interfaces.UpdateEntity;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.UService;

public interface UServiceInstance<E extends UpdateEntity, ID extends Comparable<ID>> {
    UService<E, ID> Modify();


}
