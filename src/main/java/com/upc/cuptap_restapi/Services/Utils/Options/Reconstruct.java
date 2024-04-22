package com.upc.cuptap_restapi.Services.Utils.Options;

import com.upc.cuptap_restapi.Models.Interfaces.DTO.RequestDTO;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.CreateEntity;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.Entity;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.ReadEntity;

public interface Reconstruct<E extends Entity, R extends RequestDTO<E>> {
    E Reconstruct(R requestDTO);
}
