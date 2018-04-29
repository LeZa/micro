package com.eureka.me.client.service;

import java.io.Serializable;

public class AuthorSearchCriteria
    implements Serializable{

    private static final long serialVersionUID = 1L;


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuthorSearchCriteria() {
    }

    public AuthorSearchCriteria(String name) {
        this.name = name;
    }
}
