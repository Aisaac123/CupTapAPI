package com.upc.cuptap_restapi.Controller.Instances;

import com.upc.cuptap_restapi.Controller.General.Implementation.CController;
import com.upc.cuptap_restapi.Models.Interface.CreateEntity;

public interface CControllerInstance<E extends CreateEntity, ID extends Comparable<ID>> {
    CController<E, ID> Persist();
}
