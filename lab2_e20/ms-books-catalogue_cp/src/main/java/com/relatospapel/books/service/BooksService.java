package com.relatospapel.books.service;

import java.util.List;

import com.relatospapel.books.controller.model.BookSearchRequest;
import com.relatospapel.books.data.model.Book;
import com.relatospapel.books.controller.model.BookDto;
import com.relatospapel.books.controller.model.CreateBookRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BooksService {

	Page<Book> getBooks(BookSearchRequest request, Pageable pageable);

	List<Book> getBooks(BookSearchRequest request);

	Book getBook(String bookId);

	Boolean removeBook(String bookId);

	Book createBook(CreateBookRequest request);

	Book updateBook(String bookId, String updateRequest);

	Book updateBook(String bookId, BookDto updateRequest);

}
