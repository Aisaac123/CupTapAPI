package com.upc.cuptap_restapi.Controller.Instances;

import com.upc.cuptap_restapi.Controller.General.Implementation.UController;
import com.upc.cuptap_restapi.Models.Interface.UpdateEntity;

public interface UControllerInstance<E extends UpdateEntity, ID extends Comparable<ID>> {
    UController<E, ID> Modify();

}
