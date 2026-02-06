package com.relatospapel.books.data;

import java.util.List;

import com.relatospapel.books.data.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface BookJpaRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

	List<Book> findByTitle(String name);

	List<Book> findByAuthor(String country);

	List<Book> findByVisible(Boolean visible);

	List<Book> findByPublicationDate(String publicationDate);

}
