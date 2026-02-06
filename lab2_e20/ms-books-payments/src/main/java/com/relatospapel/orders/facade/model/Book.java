package com.relatospapel.orders.facade.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Book {
	private Long id;
	private String title;
	private String author;
	private String publicationDate;
    private String category;
    private String cod_ISBN;
    private String rate;
	private Boolean visible;
}
