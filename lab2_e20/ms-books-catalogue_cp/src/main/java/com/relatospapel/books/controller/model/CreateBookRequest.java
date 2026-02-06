package com.relatospapel.books.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {

    private String title;
    private String author;
    private String publicationDate;
    private String category;
    private String cod_ISBN;
    private String rate;
	private Boolean visible;
}
