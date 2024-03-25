package com.upc.cuptap_restapi.Services.DataAccess.DASIntances;

import com.upc.cuptap_restapi.Models.Interfaces.ReadEntity;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.RService;

public interface RServiceInstance<E extends ReadEntity, ID extends Comparable<ID>> {
    RService<E, ID> Read();
}
