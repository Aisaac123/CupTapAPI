package com.upc.cuptap_restapi.Models.Interfaces.DTO;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.Entity;

public interface IDTO<E extends Entity> {

    E toEntity();
}
