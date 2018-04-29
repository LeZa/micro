package com.eureka.me.client.service;

import com.eureka.me.client.domain.Author;
import com.eureka.me.client.domain.Book;

public interface BookService {

    Book getBook(Author author, String name);
}
