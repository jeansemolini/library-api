package com.jeansemolini.libraryapi.service.impl;

import com.jeansemolini.libraryapi.exception.BusinnessException;
import com.jeansemolini.libraryapi.model.entity.Book;
import com.jeansemolini.libraryapi.api.service.BookService;
import com.jeansemolini.libraryapi.model.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
