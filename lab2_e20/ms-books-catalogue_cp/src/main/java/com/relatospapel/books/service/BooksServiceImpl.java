package com.relatospapel.books.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.relatospapel.books.data.BookRepository;
import com.relatospapel.books.controller.model.BookDto;
import com.relatospapel.books.data.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.relatospapel.books.controller.model.CreateBookRequest;

@Service
@Slf4j
public class BooksServiceImpl implements BooksService {

	@Autowired
	private BookRepository repository;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public List<Book> getBooks(String title, String author, String publicationDate, String category, String cod_ISBN, String rate,  Boolean visible) {

		if (StringUtils.hasLength(title) || StringUtils.hasLength(author) || StringUtils.hasLength(publicationDate)|| StringUtils.hasLength(category)
                || StringUtils.hasLength(cod_ISBN) || StringUtils.hasLength(rate)
				|| visible != null) {
			return repository.search(title, author, publicationDate, category,cod_ISBN,rate,visible);
		}

		List<Book> books = repository.getBooks();
		return books.isEmpty() ? null : books;
	}

	@Override
	public Book getBook(String BookId) {
		return repository.getById(Long.valueOf(BookId));
	}

	@Override
	public Boolean removeBook(String BookId) {

		Book book = repository.getById(Long.valueOf(BookId));

		if (book != null) {
			repository.delete(book);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public Book createBook(CreateBookRequest request) {

		//Otra opcion: Jakarta Validation: https://www.baeldung.com/java-validation
		if (request != null 
                && StringUtils.hasLength(request.getTitle().trim())
				&& StringUtils.hasLength(request.getAuthor().trim())
				&& StringUtils.hasLength(request.getPublicationDate().trim())
                && StringUtils.hasLength(request.getCategory().trim())
                && StringUtils.hasLength(request.getCod_ISBN().trim())
                && StringUtils.hasLength(request.getRate().trim())
                && request.getVisible() != null) {

			Book book = Book.builder().title(request.getTitle()).author(request.getAuthor())
					.publicationDate(request.getPublicationDate()).category(request.getCategory())
                    .cod_isbn(request.getCod_ISBN()).rate(request.getRate())
                    .visible(request.getVisible()).build();

			return repository.save(book);
		} else {
			return null;
		}
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
