package com.upc.cuptap_restapi.Controllers.Controller;


import com.upc.cuptap_restapi.Controllers.DataAccess.DACInstances.RControllerInstance;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.RController;
import com.upc.cuptap_restapi.Models.Entities.Estado;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import com.upc.cuptap_restapi.Services.Service.EstadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/CupTapAPI/v1/Estados")
@Tag(name = "Estados del pedido", description = "Controlador de estados para el modulo de pedidos")
public class EstadoController implements RControllerInstance<Estado, String> {
    final
    EstadoService serv;

    public EstadoController(EstadoService serv) {
        this.serv = serv;
    }


    @Override
    public RController<Estado, String> Read() {
        return new RController<>(serv.Read());
    }


    @GetMapping("")
    @Operation(summary = "Consulta los estados registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra todos los estados del pedido registrados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<List<Estado>>> GetAll() {
        return Read().GetAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta estados por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra los datos del estado del pedido correspondiente"),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Estado>> GetById(@PathVariable String id) {
        return Read().GetById(id);
    }

    @GetMapping("/{page_index}/{limit}")
    @Operation(summary = "Consulta de estados (Paginacion)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra la pagina con los estados del pedido solicitados"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Page<Estado>>> GetPageable(@PathVariable int page_index, @PathVariable int limit) {
        return Read().GetPageable(page_index, limit);
    }


}
