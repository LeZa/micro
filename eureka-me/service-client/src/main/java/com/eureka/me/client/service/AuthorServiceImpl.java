package com.eureka.me.client.service;

import com.eureka.me.client.domain.Author;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;


@Component("authorService")
@Transactional
public class AuthorServiceImpl
    implements  AuthorService{

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public String findAll(AuthorSearchCriteria criteria, Pageable pageable) {
        Page<Author>  page = this.authorRepository.findAll(null);

     /*   Iterator<Author> iterator = page.iterator();
        while( iterator.hasNext() ){
            Author author = iterator.next();
                    System.out.println(author.toString());
        }*/
     List<Author> resultList = page.getContent();
     Author author = resultList.get(0);
        JsonObject returnData = new JsonParser().parse(author.toString()).getAsJsonObject();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        System.out.println(gson.toJson(author));
        return gson.toJson(author);
    }
}
