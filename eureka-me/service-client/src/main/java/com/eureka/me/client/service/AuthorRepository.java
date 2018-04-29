package com.eureka.me.client.service;

import com.eureka.me.client.domain.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

interface AuthorRepository
        extends Repository<Author,String>{
        Page<Author> findAll(Pageable pageable);
    }

