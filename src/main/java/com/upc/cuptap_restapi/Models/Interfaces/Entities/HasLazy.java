package com.upc.cuptap_restapi.Models.Interfaces.Entities;

import com.upc.cuptap_restapi.Models.Interfaces.DTO.LazyDTO;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.Entity;

public interface HasLazy<DTO extends LazyDTO> extends Entity {
    DTO toLazy();
}
