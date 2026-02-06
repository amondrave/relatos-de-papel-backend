package com.relatospapel.books.data;

import com.relatospapel.books.controller.model.BookSearchRequest;
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

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final BookJpaRepository repository;

    public Page<Book> getBooks(BookSearchRequest filters, Pageable pageable) {
        if (filters == null || !filters.isEmpty()) {
            return repository.findAll(pageable);
        }
        return repository.findAll(buildSpec(filters), pageable);
    }

    public List<Book> getBooks(BookSearchRequest filters) {
        if (filters == null || !filters.isEmpty()) {
            return repository.findAll();
        }
        return repository.findAll(buildSpec(filters));
    }

    private SearchCriteria<Book> buildSpec(BookSearchRequest f) {
        SearchCriteria<Book> spec = new SearchCriteria<>();
        addParam(spec, Consts.TITLE, f.title(), SearchOperation.MATCH);
        addParam(spec, Consts.AUTHOR, f.author(), SearchOperation.MATCH);
        addParam(spec, Consts.PUBLICATIONDATE, f.publicationDate(), SearchOperation.EQUAL);
        addParam(spec, Consts.CATEGORY, f.category(), SearchOperation.MATCH);
        addParam(spec, Consts.ISBN, f.codIsbn(), SearchOperation.MATCH);
        addParam(spec, Consts.RATE, f.rate(), SearchOperation.EQUAL);
        addParam(spec, Consts.VISIBLE, f.visible(), SearchOperation.EQUAL);
        return spec;
    }

    private void addParam(SearchCriteria<Book> spec, String key, Object val, SearchOperation op) {
        if (val != null && (!(val instanceof String s) || StringUtils.isNotBlank(s))) {
            spec.add(new SearchStatement(key, val, op));
        }
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
}
