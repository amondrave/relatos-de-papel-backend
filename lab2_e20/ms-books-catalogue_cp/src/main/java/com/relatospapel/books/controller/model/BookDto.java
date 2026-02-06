package com.relatospapel.books.controller.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookDto {

    private String title;
    private String author;
    private String publicationDate;
    private String category;
    private String cod_ISBN;
    private String rate;
	private Boolean visible;

}
