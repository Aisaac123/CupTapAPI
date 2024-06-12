package com.upc.cuptap_restapi.Models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthModel {

    public AuthModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthModel() {
    }

    String username;

    String password;
}
