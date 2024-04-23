package com.upc.cuptap_restapi.Models.Utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor()
@Setter
@Getter
public class ResponseBuilder<T> extends Response<T> {

    public static <T> Response<T> Error(@NotNull Exception exception) {
        return (Response<T>) new ResponseBuilder().withMsg(exception.getMessage()).withSuccess(false);
    }

    public static <T> Response<T> Fail(String fail_message) {
        return (Response<T>) new ResponseBuilder().withMsg(fail_message).withSuccess(false);
    }

    public static <T> Response<T> Success(String succes) {
        return (Response<T>) new ResponseBuilder().withMsg(succes).withSuccess(true);
    }

    public static <T> Response<T> Success() {
        return (Response<T>) new ResponseBuilder().withSuccess(true);
    }


    public ResponseBuilder<T> withMsg(String value) {
        msg = value;
        return this;
    }

    public ResponseBuilder<T> withData(T value) {
        data = value;
        return this;
    }

    public ResponseBuilder<T> withSuccess(boolean value) {
        success = value;
        return this;
    }

}
