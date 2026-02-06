package com.relatospapel.books.controller.model;

import java.time.LocalDate;
import java.util.stream.Stream;

public record BookSearchRequest(
        String title,
        String author,
        LocalDate publicationDate,
        String category,
        String codIsbn,
        Double rate,
        Boolean visible
) {
    /**
     * Verifica si el objeto está vacío (todos los filtros son null o vacíos).
     */
    public boolean isEmpty() {
        return Stream.of(title, author, category, codIsbn).allMatch(org.apache.commons.lang3.StringUtils::isBlank)
                && publicationDate == null
                && rate == null
                && visible == null;
    }
}
