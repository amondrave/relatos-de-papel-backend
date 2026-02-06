package com.relatosdepapel.payments.api;

import com.relatosdepapel.payments.api.dto.PaymentRequest;
import com.relatosdepapel.payments.api.dto.PaymentResponse;
import com.relatosdepapel.payments.service.PaymentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@Tag(
        name = "Payments",
        description = "Microservicio operador de pagos/compras. Registra compras y valida libros contra el catálogo."
)
public class PaymentsController {

 private final PaymentsService service;

 /**
  * Registers a purchase after validating books in the catalogue service.
  */
 @PostMapping
 @ResponseStatus(HttpStatus.CREATED)
 @Operation(summary = "Registrar una compra", description = "Registra una compra tras validar libros contra el servicio de catálogo.")
 @ApiResponse(
         responseCode = "201",
         description = "Compra registrada",
         content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentResponse.class))
 )
 @ApiResponse(responseCode = "400", description = "Petición inválida")
 @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
 public PaymentResponse create(@RequestBody @Valid PaymentRequest request) {
  return service.create(request);
 }

 @GetMapping
 @Operation(summary = "Listar compras", description = "Lista todas las compras registradas.")
 @ApiResponse(
         responseCode = "200",
         description = "Listado de compras",
         content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentResponse.class))
 )
 public List<PaymentResponse> list() {
  return service.list();
 }

 @GetMapping("/{id}")
 @Operation(summary = "Obtener compra por id", description = "Obtiene el detalle de una compra por su id.")
 @ApiResponse(
         responseCode = "200",
         description = "Compra encontrada",
         content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentResponse.class))
 )
 @ApiResponse(responseCode = "404", description = "Compra no encontrada")
 public PaymentResponse get(@PathVariable Long id) {
  return service.get(id);
 }
}
