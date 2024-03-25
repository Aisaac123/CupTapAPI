package com.upc.cuptap_restapi.Models.Utilities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor()
@AllArgsConstructor()
@Getter
public class Response<T> {

    protected String msg;
    protected T data;
    protected boolean success = true;
}
