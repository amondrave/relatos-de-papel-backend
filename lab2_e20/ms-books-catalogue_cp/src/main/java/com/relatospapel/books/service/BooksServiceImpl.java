package com.relatospapel.books.service;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.relatospapel.books.data.BookRepository;
import com.relatospapel.books.controller.model.BookDto;
import com.relatospapel.books.data.model.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.relatospapel.books.controller.model.CreateBookRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class BooksServiceImpl implements BooksService {

	private final BookRepository repository;
	private final ObjectMapper objectMapper;

	@Override
	public List<Book> getBooks(String title, String author, LocalDate publicationDate, String category, String codIsbn, Double rate, Boolean visible) {

		if (StringUtils.hasLength(title) || StringUtils.hasLength(author) || publicationDate != null || StringUtils.hasLength(category)
                || StringUtils.hasLength(codIsbn) || rate != null || visible != null) {
			return repository.search(title, author, publicationDate, category, codIsbn, rate, visible);
		}

		List<Book> books = repository.getBooks();
		return books.isEmpty() ? null : books;
	}

	@Override
	public Page<Book> getBooks(String title, String author, LocalDate publicationDate, String category, String codIsbn, Double rate, Boolean visible, Pageable pageable) {

		if (StringUtils.hasLength(title) || StringUtils.hasLength(author) || publicationDate != null || StringUtils.hasLength(category)
                || StringUtils.hasLength(codIsbn) || rate != null || visible != null) {
			return repository.search(title, author, publicationDate, category, codIsbn, rate, visible, pageable);
		}

		return repository.getBooks(pageable);
	}

	@Override
	public Book getBook(String bookId) {
		return repository.getById(Long.valueOf(bookId));
	}

	@Override
	public Boolean removeBook(String bookId) {

		Book book = repository.getById(Long.valueOf(bookId));

		if (book != null) {
			repository.delete(book);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public Book createBook(CreateBookRequest request) {
		Book book = Book.builder()
				.title(request.getTitle())
				.author(request.getAuthor())
				.publicationDate(request.getPublicationDate())
				.category(request.getCategory())
				.codIsbn(request.getCodIsbn())
				.rate(request.getRate())
				.visible(request.getVisible())
				.build();

		return repository.save(book);
	}

	@Override
	public Book updateBook(String bookId, String request) {

		//PATCH se implementa en este caso mediante Merge Patch: https://datatracker.ietf.org/doc/html/rfc7386
		Book book = repository.getById(Long.valueOf(bookId));
		if (book != null) {
			try {
				JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(request));
				JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(book)));
				Book patched = objectMapper.treeToValue(target, Book.class);
				repository.save(patched);
				return patched;
			} catch (JsonProcessingException | JsonPatchException e) {
				log.error("Error updating book {}", bookId, e);
                return null;
            }
        } else {
			return null;
		}
	}

	@Override
	public Book updateBook(String bookId, BookDto updateRequest) {
		Book book = repository.getById(Long.valueOf(bookId));
		if (book != null) {
			book.update(updateRequest);
			repository.save(book);
			return book;
		} else {
			return null;
		}
	}

}
