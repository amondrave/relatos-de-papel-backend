package com.relatospapel.books.data;

import com.relatospapel.books.data.model.Book;
import com.relatospapel.books.data.utils.Consts;
import com.relatospapel.books.data.utils.SearchCriteria;
import com.relatospapel.books.data.utils.SearchOperation;
import com.relatospapel.books.data.utils.SearchStatement;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final BookJpaRepository repository;

    public List<Book> getBooks() {
        return repository.findAll();
    }

    public Page<Book> getBooks(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Book getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Book save(Book book) {
        return repository.save(book);
    }

    public void delete(Book book) {
        repository.delete(book);
    }

    public List<Book> search(String title, String author, LocalDate publicationDate, String category, String codIsbn, Double rate, Boolean visible) {
        SearchCriteria<Book> spec = new SearchCriteria<>();

        if (StringUtils.isNotBlank(title)) {
            spec.add(new SearchStatement(Consts.TITLE, title, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(author)) {
            spec.add(new SearchStatement(Consts.AUTHOR, author, SearchOperation.MATCH));
        }

        if (publicationDate != null) {
            spec.add(new SearchStatement(Consts.PUBLICATIONDATE, publicationDate, SearchOperation.EQUAL));
        }

        if (StringUtils.isNotBlank(category)) {
            spec.add(new SearchStatement(Consts.CATEGORY, category, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(codIsbn)) {
            spec.add(new SearchStatement(Consts.ISBN, codIsbn, SearchOperation.MATCH));
        }

        if (rate != null) {
            spec.add(new SearchStatement(Consts.RATE, rate, SearchOperation.EQUAL));
        }

        if (visible != null) {
            spec.add(new SearchStatement(Consts.VISIBLE, visible, SearchOperation.EQUAL));
        }

        return repository.findAll(spec);
    }

    public Page<Book> search(String title, String author, LocalDate publicationDate, String category, String codIsbn, Double rate, Boolean visible, Pageable pageable) {
        SearchCriteria<Book> spec = new SearchCriteria<>();

        if (StringUtils.isNotBlank(title)) {
            spec.add(new SearchStatement(Consts.TITLE, title, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(author)) {
            spec.add(new SearchStatement(Consts.AUTHOR, author, SearchOperation.MATCH));
        }

        if (publicationDate != null) {
            spec.add(new SearchStatement(Consts.PUBLICATIONDATE, publicationDate, SearchOperation.EQUAL));
        }

        if (StringUtils.isNotBlank(category)) {
            spec.add(new SearchStatement(Consts.CATEGORY, category, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(codIsbn)) {
            spec.add(new SearchStatement(Consts.ISBN, codIsbn, SearchOperation.MATCH));
        }

        if (rate != null) {
            spec.add(new SearchStatement(Consts.RATE, rate, SearchOperation.EQUAL));
        }

        if (visible != null) {
            spec.add(new SearchStatement(Consts.VISIBLE, visible, SearchOperation.EQUAL));
        }

        return repository.findAll(spec, pageable);
    }

}
