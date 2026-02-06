package com.relatospapel.books.controller.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {

    @NotBlank(message = "El título es obligatorio")
    private String title;

    @NotBlank(message = "El autor es obligatorio")
    private String author;

    @NotNull(message = "La fecha de publicación es obligatoria")
    private LocalDate publicationDate;

    @NotBlank(message = "La categoría es obligatoria")
    private String category;

    @NotBlank(message = "El código ISBN es obligatorio")
    private String codIsbn;

    @NotNull(message = "La calificación es obligatoria")
    @Min(value = 0, message = "La calificación mínima es 0")
    @Max(value = 5, message = "La calificación máxima es 5")
    private Double rate;

    @NotNull(message = "El estado visible es obligatorio")
    private Boolean visible;
}
