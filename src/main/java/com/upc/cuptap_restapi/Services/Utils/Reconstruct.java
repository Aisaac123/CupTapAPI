package com.upc.cuptap_restapi.Services.Utils;

import com.upc.cuptap_restapi.Models.Interfaces.DTO.RequestDTO;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.Entity;

public interface Reconstruct<E extends Entity, R extends RequestDTO<E>> {
    E Reconstruct(R requestDTO);
}
