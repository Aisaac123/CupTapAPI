package com.upc.cuptap_restapi.Models.Utilities;

import io.swagger.v3.oas.annotations.media.Schema;
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

    public static class Doc {
        @Getter
        public static class InternalServerError {
            @Schema(description = "Mensaje", example = "Error interno del servidor")
            protected String msg = "Error Interno del Servidor";
            @Schema(description = "Data", example = "null")
            protected Object data;
            @Schema(description = "Success", example = "false")
            protected boolean success = false;
        }

        @Getter
        public static class BadRequest {
            @Schema(description = "Mensaje", example = "Peticion Incorrecta")
            protected String msg = "Peticion incorrecta";
            @Schema(description = "Data", example = "null")
            protected Object data;
            @Schema(description = "Success", example = "false")
            protected boolean success = false;
        }

        @Getter
        public static class NotFound {
            @Schema(description = "Mensaje", example = "No se ha encontrado el elemento por su id")
            protected String msg = "No se ha encontrado el elemento por su id";
            @Schema(description = "Data", example = "null")
            protected Object data;
            @Schema(description = "Success", example = "false")
            protected boolean success = false;
        }
    }
}
