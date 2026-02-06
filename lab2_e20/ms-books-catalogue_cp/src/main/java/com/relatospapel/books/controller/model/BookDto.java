package com.relatospapel.books.controller.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookDto {

    private String title;
    private String author;
    private LocalDate publicationDate;
    private String category;
    private String codIsbn;

    @Min(value = 0, message = "La calificación mínima es 0")
    @Max(value = 5, message = "La calificación máxima es 5")
    private Double rate;
    private Boolean visible;

}
