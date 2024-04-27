package com.upc.cuptap_restapi.Controllers.Controller;


import com.upc.cuptap_restapi.Controllers.Shared.Implements.CController;
import com.upc.cuptap_restapi.Controllers.Shared.Implements.DController;
import com.upc.cuptap_restapi.Controllers.Shared.Instances.CControllerInstance;
import com.upc.cuptap_restapi.Controllers.Shared.Instances.DControllerInstance;
import com.upc.cuptap_restapi.Models.DTO.DTORequest.Productos_ComboRequest;
import com.upc.cuptap_restapi.Models.Entities.Productos_Combo;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Models.Utils.ResponseBuilder;
import com.upc.cuptap_restapi.Services.Logic.Productos_ComboService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/ProductosCombos")
@Tag(name = "Productos del combo", description = "Controlador de productos relacionados a un combo del modulo de productos y combos")

public class Productos_ComboController implements
        CControllerInstance<Productos_Combo, Long>,
        DControllerInstance<Productos_Combo, Long> {

    final
    Productos_ComboService serv;

    public Productos_ComboController(Productos_ComboService serv) {
        this.serv = serv;
    }

    final

    @Override
    public CController<Productos_Combo, Long> Persist() {
        return new CController<>(serv.Persist());
    }

    @Override
    public DController<Productos_Combo, Long> Remove() {
        return new DController<>(serv.Remove());
    }

    @PostMapping("")
    @Operation(summary = "Permite añadir productos al combo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se agregó correctamente el pedido"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Productos_Combo>> Save(@RequestBody Productos_ComboRequest entity) {
        return Persist().Save(serv.reconstruct(entity));
    }

    @PatchMapping("/{id}/cantidad")
    @Operation(summary = "Permite actualizar la cantidad de productos de un combo por medio de su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizo correctamente los datos del pedido"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "404", description = "No se encontro el producto que pertenece a un combo por su id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<String>> Update(@PathVariable Long id, @RequestParam int cant) {
        try {
            var response = serv.PatchCantidad(id, cant);
            HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(response, status);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseBuilder.Error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Permite eliminar el producto del combo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se eliminó correctamente el detalle del pedido"),
            @ApiResponse(responseCode = "404", description = "No se encontro el producto que pertenece a un combo por su id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Productos_Combo>> Delete(@PathVariable Long id) {
        return Remove().Delete(id);
    }

    // TODO Controladores especificos

}
