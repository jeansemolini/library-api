package com.jeansemolini.libraryapi.model.repository;

import com.jeansemolini.libraryapi.model.entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest //ele cria uma instancia do banco de dados em memoria apenas para os testes e depois apaga
public class BookRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    BookRepository repository;

    @Test
    @DisplayName("Deve retornar verdadeiro quando existir um livro na base com o isbn informado")
    public void returnTrueWhenIsbnExists() {
        //cenario
        String isbn = "123";
        Book book = Book.builder().title("Aventuras").author("Fulano").isbn(isbn).build();
        entityManager.persist(book);

        //execucaso
        boolean exists = repository.existsByIsbn(isbn);

        //verificacao
        Assertions.assertTrue(exists);
    }

    @Test
    @DisplayName("Deve retornar false quando existir um livro na base com o isbn informado")
    public void returnTrueWhenIsbnDoesntExists() {
        //cenario
        String isbn = "123";

        //execucaso
        boolean exists = repository.existsByIsbn(isbn);

        //verificacao
        Assertions.assertFalse(exists);
    }
}
