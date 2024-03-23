package com.upc.cuptap_restapi.Services.General.Instances;

import com.upc.cuptap_restapi.Models.Interface.DeleteEnity;
import com.upc.cuptap_restapi.Repository.Repositories.GlobalRepository;
import com.upc.cuptap_restapi.Services.General.GenericServices.DService;

public interface DInstance<E extends DeleteEnity, ID extends Comparable<ID>> {
    DService<E, ID> Remove();



}
