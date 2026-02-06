package com.relatospapel.books.service;

import java.time.LocalDate;
import java.util.List;

import com.relatospapel.books.data.model.Book;
import com.relatospapel.books.controller.model.BookDto;
import com.relatospapel.books.controller.model.CreateBookRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BooksService {

	List<Book> getBooks(String title, String author, LocalDate publicationDate, String category, String codIsbn, Double rate, Boolean visible);

	Page<Book> getBooks(String title, String author, LocalDate publicationDate, String category, String codIsbn, Double rate, Boolean visible, Pageable pageable);

	Book getBook(String bookId);

	Boolean removeBook(String bookId);

	Book createBook(CreateBookRequest request);

	Book updateBook(String bookId, String updateRequest);

	Book updateBook(String bookId, BookDto updateRequest);

}
