package com.relatosdepapel.payments.integration.catalogue;

import lombok.Data;

/**
 * Modelo mínimo que espera el operador desde ms-books-catalogue.
 * menciona validaciones como visibilidad y stock.
 */
@Data
public class Book {
 private Long id;
 private Boolean visible;
 private Integer stock;
}
