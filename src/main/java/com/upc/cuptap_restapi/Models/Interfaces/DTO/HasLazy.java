package com.upc.cuptap_restapi.Models.Interfaces.DTO;

public interface HasLazy<DTO extends LazyDTO> extends IDTO {
    DTO toLazy();
}
