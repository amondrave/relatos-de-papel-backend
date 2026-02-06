package com.relatospapel.books.data;

import java.time.LocalDate;
import java.util.List;

import com.relatospapel.books.data.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface BookJpaRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    List<Book> findByTitle(String name);

    List<Book> findByAuthor(String author);

    List<Book> findByVisible(Boolean visible);

    List<Book> findByPublicationDate(LocalDate publicationDate);

    boolean existsByTitle(String title);

    boolean existsByTitleAndIdNot(String title, Long id);

	Page<Book> findAll(Pageable pageable);

}
