package com.upc.cuptap_restapi.Controllers.Controller;


import com.upc.cuptap_restapi.Controllers.Providers.Providers.CController;
import com.upc.cuptap_restapi.Controllers.Providers.Providers.DController;
import com.upc.cuptap_restapi.Controllers.Providers.Providers.RController;
import com.upc.cuptap_restapi.Controllers.Providers.Providers.UController;
import com.upc.cuptap_restapi.Controllers.Providers.ProvidersInstances.CRUDControllerInstance;
import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Services.Logic.DetallesPedidoService;
import com.upc.cuptap_restapi.Services.Middlewares.ReconstructRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/detalles")
@Tag(name = "Detalles del pedido", description = "Controlador del modulo de Detalles del pedido")
public class DetallesPedidoController implements CRUDControllerInstance<DetallesPedido, Long> {
    final
    DetallesPedidoService serv;
    final
    ReconstructRequest reconstruct;

    public DetallesPedidoController(DetallesPedidoService serv, ReconstructRequest reconstruct) {
        this.serv = serv;
        this.reconstruct = reconstruct;
    }

    @Override
    public CController<DetallesPedido, Long> Persist() {
        return new CController<>(serv.Persist());
    }

    @Override
    public DController<DetallesPedido, Long> Remove() {
        return new DController<>(serv.Remove());
    }

    @Override
    public RController<DetallesPedido, Long> Read() {
        return new RController<>(serv.Read());
    }

    @Override
    public UController<DetallesPedido, Long> Modify() {
        return new UController<>(serv.Modify());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta detalles del pedido por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra los datos de los detalles del pedido correspondiente"),
            @ApiResponse(responseCode = "404", description = "No se encontro el detalle del pedido por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<DetallesPedido>> GetById(@PathVariable Long id, @RequestParam(value = "lazy", required = false) boolean isLazy) {
        return Read().GetById(id, isLazy);
    }

    @GetMapping("")
    @Operation(summary = "Consulta de combos (Paginacion)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra la pagina con los combos solicitados"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response> GetAll(@RequestParam(value = "index", defaultValue = "-1", required = false) int page_index,
                                           @RequestParam(value = "limit", defaultValue = "-1", required = false) int limit,
                                           @RequestParam(value = "dateLimit", required = false) LocalDate fecha,
                                           @RequestParam(value = "lazy", required = false) boolean isLazy) {
        return Read().GetAll(page_index, limit, fecha, isLazy);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Permite eliminar detalles del pedido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se eliminó correctamente el detalle del pedido"),
            @ApiResponse(responseCode = "404", description = "No se encontro el detalle del pedido por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<DetallesPedido>> Delete(@PathVariable Long id) {
        return Remove().Delete(id);
    }

    // TODO Controladores especificos

}
