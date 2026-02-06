package com.relatospapel.books.data.model;

import com.relatospapel.books.controller.model.BookDto;
import com.relatospapel.books.data.utils.Consts;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = Consts.TITLE, unique = true)
	private String title;
	
	@Column(name = Consts.AUTHOR)
	private String author;
	
	@Column(name = Consts.PUBLICATIONDATE)
	private String publicationDate;

    @Column(name = Consts.CATEGORY)
    private String category;

    @Column(name = Consts.ISBN)
    private String cod_isbn;
    @Column(name = Consts.RATE)
    private String rate;
	
	@Column(name = Consts.VISIBLE)
	private Boolean visible;

	public void update(BookDto bookDto) {
		this.title = bookDto.getTitle();
		this.author = bookDto.getAuthor();
		this.publicationDate = bookDto.getPublicationDate();
        this.category= bookDto.getCategory();
        this.cod_isbn= bookDto.getCod_ISBN();
        this.rate = bookDto.getRate();
		this.visible = bookDto.getVisible();
	}

}
