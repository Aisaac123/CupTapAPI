package com.upc.cuptap_restapi.Controllers.Controller;


import com.upc.cuptap_restapi.Controllers.Shared.Implements.CController;
import com.upc.cuptap_restapi.Controllers.Shared.Implements.DController;
import com.upc.cuptap_restapi.Controllers.Shared.Implements.RController;
import com.upc.cuptap_restapi.Controllers.Shared.Implements.UController;
import com.upc.cuptap_restapi.Controllers.Shared.Instances.CRUDControllerInstance;
import com.upc.cuptap_restapi.Models.DTO.DTORequest.PagoRequest;
import com.upc.cuptap_restapi.Models.Entities.Pago;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Services.Logic.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/v1/Pagos")
@Tag(name = "Pagos", description = "Controlador del modulo de pagos")
public class PagoController implements CRUDControllerInstance<Pago, Long> {

    final
    PagoService serv;

    public PagoController(PagoService serv) {
        this.serv = serv;
    }

    @Override
    public CController<Pago, Long> Persist() {
        return new CController<>(serv.Persist());
    }

    @Override
    public DController<Pago, Long> Remove() {
        return new DController<>(serv.Remove());
    }

    @Override
    public RController<Pago, Long> Read() {
        return new RController<>(serv.Read());
    }

    @Override
    public UController<Pago, Long> Modify() {
        return new UController<>(serv.Modify());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta de pagos por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra los datos de los pagos correspondiente"),
            @ApiResponse(responseCode = "404", description = "No se encontro el pago por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response> GetById(@PathVariable Long id, @RequestParam(value = "lazy", required = false) boolean isLazy) {
        return Read().GetById(id, isLazy);
    }

    @GetMapping("")
    @Operation(summary = "Consulta de pagos (Paginacion)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra la pagina con los pagos solicitados"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response> GetAll(@RequestParam(value = "index", defaultValue = "-1", required = false) int page_index,
                                           @RequestParam(value = "limit", defaultValue = "-1", required = false) int limit,
                                           @RequestParam(value = "dateLimit", required = false) LocalDate fecha,
                                           @RequestParam(value = "lazy", required = false) boolean isLazy) {
        return Read().GetAll(page_index, limit, fecha, isLazy);
    }

    @PostMapping("")
    @Operation(summary = "Permite registrar pagos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se agregó correctamente el pago"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Pago>> Save(@RequestBody PagoRequest entity) {
        return Persist().Save(serv.reconstruct(entity));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Permite actualizar los datos de los pagos por medio de su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizo correctamente los datos de los pagos"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "404", description = "No se encontro el pago por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Map<String, Pago>>> Update(@PathVariable Long id, @RequestBody PagoRequest new_entity) {
        return Modify().Update(serv.reconstruct(new_entity), id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Permite eliminar un pago por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se eliminó correctamente el pago"),
            @ApiResponse(responseCode = "404", description = "No se encontro el pago por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Pago>> Delete(@PathVariable Long id) {
        return Remove().Delete(id);
    }

    // TODO Controladores especificos

}
