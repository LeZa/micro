package com.eureka.me.client.service;

import com.eureka.me.client.domain.Author;
import com.eureka.me.client.domain.Book;
import org.springframework.stereotype.Component;

@Component("bookService")
public class BookServiceImpl
    implements BookService{

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getBook(Author author, String name) {
        return this.bookRepository.findByAuthorAndName(author,name);
    }
}
