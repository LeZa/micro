package com.eureka.me.client.service;

import com.eureka.me.client.domain.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AuthorService {

    String findAll(AuthorSearchCriteria criteria, Pageable pageable);
}
