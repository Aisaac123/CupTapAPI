package com.upc.cuptap_restapi.Controller.Instances;

import com.upc.cuptap_restapi.Controller.General.Implementation.RController;
import com.upc.cuptap_restapi.Models.Interface.ReadEntity;

public interface RControllerInstance<E extends ReadEntity, ID extends Comparable<ID>> {
    RController<E, ID> Read();
}
