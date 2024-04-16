package com.upc.cuptap_restapi.Controllers.Controller;

import com.upc.cuptap_restapi.Controllers.DataAccess.DACInstances.CControllerInstance;
import com.upc.cuptap_restapi.Controllers.DataAccess.DACInstances.RControllerInstance;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.CController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.RController;
import com.upc.cuptap_restapi.Models.DTO.PedidoDto;
import com.upc.cuptap_restapi.Models.DTO.UsuarioDto;
import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Entities.Promocion;
import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.IDTO;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import com.upc.cuptap_restapi.Models.Utilities.ResponseBuilder;
import com.upc.cuptap_restapi.Services.Bussiness.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/CupTapAPI/v1/Usuarios")
@Tag(name = "Usuarios", description = "Controlador del modulo de usuarios")

public class UsuarioController implements CControllerInstance<Usuario, UUID>, RControllerInstance<Usuario, UUID> {


    private final UsuarioService service;

    public UsuarioController(UsuarioService usuarioService) {
        service = usuarioService;
    }

    @Override
    public CController<Usuario, UUID> Persist() {
        return new CController<>(service.Persist());
    }

    @Override
    public RController<Usuario, UUID> Read() {
        return new RController<>(service.Read());
    }


    @GetMapping("")
    @Operation(summary = "Consulta todos los usuarios registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra los datos de todos los usuarios"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<List<Usuario>>> GetAll() {
        return Read().GetAll();
    }

    @GetMapping("/{page_index}/{limit}")
    @Operation(summary = "Consulta de usuarios (Paginacion)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra la pagina con los usuarios solicitados"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Page<Usuario>>> GetPageable(@PathVariable int page_index, @PathVariable int limit) {
        return Read().GetPageable(page_index, limit);
    }

    @PostMapping("")
    @Operation(summary = "Permite registrar usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se agregó correctamente al usuario"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Usuario>> Save(@RequestBody UsuarioDto usuario) {
        return Persist().Save(usuario.toEntity());
    }


    // Metodos especificos


    @PutMapping("/{cedula}")
    @Operation(summary = "Permite actualizar los datos del Usuario por medio de su cedula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizo correctamente los datos del usuario"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por cedula", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Map<String, Usuario>>> PutByCedula(@PathVariable String cedula, @RequestBody UsuarioDto new_user) {
        try {
            var response = service.UpdateByCedula(new_user.toEntity(), cedula);
            HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(response, status);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseBuilder.Error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{cedula}")
    @Operation(summary = "Permite eliminar Usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se eliminó correctamente al usuario"),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por cedula", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Usuario>> DeleteByCedula(@PathVariable String cedula) {
        try {
            var response = service.DeleteByCedula(cedula);
            HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(response, status);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseBuilder.Error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{cedula}")
    @Operation(summary = "Consulta usuario por su cedula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra los datos del usuario correspondiente"),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por cedula", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Usuario>> GetByCedula(@PathVariable String cedula) {
        try {
            var response = service.GetByCedula(cedula);
            HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(response, status);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseBuilder.Error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // TODO Controladores especificos


    @GetMapping("/{cedula}/pedidos")
    @Operation(summary = "Obtiene los pedidos de un usuario en especifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra los pedidos realizados del usuario correspondiente"),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por cedula", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<List<Pedido>>> GetPedidos(@PathVariable String cedula) {
        try {
            var response = service.GetPedidos(cedula);
            HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(response, status);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseBuilder.Error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{cedula}/pedidos")
    @Operation(summary = "Crea un pedido para un usuario en especifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha creado el pedido para el usuario"),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por cedula", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Pedido>> AddPedidos(@RequestBody PedidoDto pedido, @PathVariable String cedula) {
        try {
            var response = service.AddPedidoToUsuario(cedula, pedido.toEntity());
            HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(response, status);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseBuilder.Error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
