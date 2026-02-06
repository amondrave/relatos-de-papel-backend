package com.relatosdepapel.payments.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * Receives a list of book ids and an optional customer identifier.
 */
public record PaymentRequest(
    @NotNull(message = "`books` no puede ser null")
    @NotEmpty(message = "`books` no puede ser vacío")
    List<String> books,

    @Size(max = 120, message = "`customer` máximo 120 caracteres")
    String customer
) {}
