package com.upc.cuptap_restapi.Models.Interfaces.DTO;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.Entity;

public interface RequestDTO<E extends Entity> extends IDTO {

    E toEntity();
}
