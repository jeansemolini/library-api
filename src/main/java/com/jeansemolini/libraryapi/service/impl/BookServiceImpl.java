package com.jeansemolini.libraryapi.service.impl;

import com.jeansemolini.libraryapi.api.service.BookService;
import com.jeansemolini.libraryapi.exception.BusinnessException;
import com.jeansemolini.libraryapi.model.entity.Book;
import com.jeansemolini.libraryapi.model.repository.BookRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    //@Autowired
    private BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {
        if (repository.existsByIsbn(book.getIsbn())) {
            throw new BusinnessException("Isbn já cadastrado.");
        }
        return repository.save(book);
    }

    @Override
    public Optional<Book> getById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public void delete(Book book){
        if (book == null || book.getId() == null) {
            throw new IllegalArgumentException("Book id cant be null");
        }
        this.repository.delete(book);
    }

    @Override
    public Book update(Book book) {
        if (book == null || book.getId() == null) {
            throw new IllegalArgumentException("Book id cant be null");
        }
        return this.repository.save(book);
    }

    @Override
    public Page<Book> find(Book filter, Pageable pageRequest) {
        Example<Book> example = Example.of(filter,
                ExampleMatcher
                        .matching()
                        .withIgnoreCase() //ignora os campos string case sensitive;
                        .withIgnoreNullValues() //ignora valores nulos
                        .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING ) ); //verifica o conteudo das propriedades

        return repository.findAll(example, pageRequest);
    }

    @Override
    public Optional<Book> getBookByIsbn(String isbn) {
        return null;
    }
}
