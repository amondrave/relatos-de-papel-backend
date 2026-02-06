package com.relatosdepapel.payments.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePaymentRequest(
        @Size(max = 120, message = "`customer` máximo 120 caracteres")
        @NotBlank String customer
) {}
