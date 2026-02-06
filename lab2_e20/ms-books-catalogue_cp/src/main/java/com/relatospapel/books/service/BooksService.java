package com.relatospapel.books.service;

import java.util.List;

import com.relatospapel.books.data.model.Book;
import com.relatospapel.books.controller.model.BookDto;
import com.relatospapel.books.controller.model.CreateBookRequest;

public interface BooksService {
	
	List<Book> getBooks(String title, String author, String publicationDate, String category, String cod_ISBN, String rate,  Boolean visible);
	
	Book getBook(String productId);
	
	Boolean removeBook(String productId);
	
	Book createBook(CreateBookRequest request);

	Book updateBook(String productId, String updateRequest);

	Book updateBook(String productId, BookDto updateRequest);

}
