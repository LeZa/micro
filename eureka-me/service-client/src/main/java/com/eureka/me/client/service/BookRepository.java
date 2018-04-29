package com.eureka.me.client.service;

import com.eureka.me.client.domain.Author;
import com.eureka.me.client.domain.Book;
import org.springframework.data.repository.Repository;

interface  BookRepository
    extends Repository<Book,Integer>{

    /**
     * @Description 根據作者和名稱查詢一本書
     * @param authro
     * @param name
     * @return
     */
    Book findByAuthorAndName(Author authro, String name);
}
