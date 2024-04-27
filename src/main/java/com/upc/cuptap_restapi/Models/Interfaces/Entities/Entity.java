package com.upc.cuptap_restapi.Models.Interfaces.Entities;

import java.time.LocalDateTime;

public interface Entity {
    LocalDateTime getFechaRegistro();

    default Entity NotLazy(){
        return null;
    }

}
