package com.relatosdepapel.payments.api.dto;

import java.time.Instant;
import java.util.List;

public record PaymentResponse(
    Long id,
    List<Long> books,
    String customer,
    Instant createdAt
) {}
