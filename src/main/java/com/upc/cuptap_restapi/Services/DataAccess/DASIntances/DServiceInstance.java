package com.upc.cuptap_restapi.Services.DataAccess.DASIntances;

import com.upc.cuptap_restapi.Models.Interfaces.DeleteEnity;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.DService;

public interface DServiceInstance<E extends DeleteEnity, ID extends Comparable<ID>> {
    DService<E, ID> Remove();


}
