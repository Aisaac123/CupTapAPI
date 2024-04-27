package com.upc.cuptap_restapi.Controllers.Controller;


import com.upc.cuptap_restapi.Controllers.Shared.Implements.CController;
import com.upc.cuptap_restapi.Controllers.Shared.Implements.DController;
import com.upc.cuptap_restapi.Controllers.Shared.Implements.RController;
import com.upc.cuptap_restapi.Controllers.Shared.Implements.UController;
import com.upc.cuptap_restapi.Controllers.Shared.Instances.CRUDControllerInstance;
import com.upc.cuptap_restapi.Models.DTO.DTORequest.PedidoRequest;
import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Services.Logic.PedidoService;
import com.upc.cuptap_restapi.Events.Event.PedidoEventListener;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/v1/Pedidos")
@Tag(name = "Pedidos", description = "Controlador del modulo de pedidos")

public class PedidoController implements CRUDControllerInstance<Pedido, Long> {

    final
    PedidoService serv;
    final
    PedidoEventListener listener;
    public PedidoController(PedidoService serv, PedidoEventListener listener) {
        this.serv = serv;
        this.listener = listener;
    }

    @Override
    public CController<Pedido, Long> Persist() {
        return new CController<>(serv.Persist());
    }

    @Override
    public DController<Pedido, Long> Remove() {
        return new DController<>(serv.Remove());
    }

    @Override
    public RController<Pedido, Long> Read() {
        return new RController<>(serv.Read());
    }

    @Override
    public UController<Pedido, Long> Modify() {
        return new UController<>(serv.Modify());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta de pedidos por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra los datos de los pedidos correspondiente"),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response> GetById(@PathVariable Long id, @RequestParam(value = "lazy", required = false) boolean isLazy) {
        return Read().GetById(id, isLazy);
    }

    @GetMapping("")
    @Operation(summary = "Consulta de pedidos (Paginacion)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra la pagina con los pedidos solicitados"),
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
    @Operation(summary = "Permite registrar pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se agregó correctamente el pedido"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Pedido>> Save(@RequestBody PedidoRequest entity) {
        return Persist().Save(serv.reconstruct(entity.toEntity()), true);
    }

    @PatchMapping("/{id}/estado/{estado}")
    @Operation(summary = "Permite actualizar los datos del pedido por medio de su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizo correctamente los datos del pedido"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "404", description = "No se encontro el pedido por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response> Update(@PathVariable Long id, @PathVariable String estado) {
        var res = serv.chageEstado(estado, id);
        var status = res.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(res, status);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Permite eliminar el pedido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se eliminó correctamente el detalle del pedido"),
            @ApiResponse(responseCode = "404", description = "No se encontro el pedido por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Pedido>> Delete(@PathVariable Long id) {
        return Remove().Delete(id, true);
    }

    // TODO Controladores especificos

}
