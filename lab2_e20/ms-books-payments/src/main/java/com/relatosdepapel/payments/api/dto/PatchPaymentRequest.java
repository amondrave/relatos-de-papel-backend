package com.relatosdepapel.payments.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record PatchPaymentRequest(
        @Size(max = 120, message = "`customer` máximo 120 caracteres")
        String customer,

        @NotNull(message = "`books` no puede ser null")
        @NotEmpty(message = "`books` no puede ser vacío")
        List<String> books
) {}