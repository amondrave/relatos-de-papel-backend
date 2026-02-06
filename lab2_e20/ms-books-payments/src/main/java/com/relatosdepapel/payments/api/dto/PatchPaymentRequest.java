package com.relatosdepapel.payments.api.dto;

import jakarta.validation.constraints.Size;

public record PatchPaymentRequest(
        @Size(max = 120, message = "`customer` máximo 120 caracteres")
        String customer
) {}