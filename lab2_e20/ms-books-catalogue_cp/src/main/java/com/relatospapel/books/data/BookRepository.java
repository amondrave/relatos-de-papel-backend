package com.relatospapel.books.data;

import com.relatospapel.books.data.model.Book;
import com.relatospapel.books.data.utils.Consts;
import com.relatospapel.books.data.utils.SearchCriteria;
import com.relatospapel.books.data.utils.SearchOperation;
import com.relatospapel.books.data.utils.SearchStatement;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final BookJpaRepository repository;

    public List<Book> getBooks() {
        return repository.findAll();
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

    public List<Book> search(String title, String author, String publicationDate, String category, String cod_ISBN, String rate,  Boolean visible) {
        SearchCriteria<Book> spec = new SearchCriteria<>();

        if (StringUtils.isNotBlank(title)) {
            spec.add(new SearchStatement(Consts.TITLE, title, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(author)) {
            spec.add(new SearchStatement(Consts.AUTHOR, author, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(publicationDate)) {
            spec.add(new SearchStatement(Consts.PUBLICATIONDATE, publicationDate, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(category)) {
            spec.add(new SearchStatement(Consts.CATEGORY, category, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(cod_ISBN)) {
            spec.add(new SearchStatement(Consts.ISBN, cod_ISBN, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(rate)) {
            spec.add(new SearchStatement(Consts.RATE, rate, SearchOperation.MATCH));
        }

        if (visible != null) {
            spec.add(new SearchStatement(Consts.VISIBLE, visible, SearchOperation.EQUAL));
        }

        return repository.findAll(spec);
    }

}
