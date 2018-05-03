package com.eureka.me.client.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Author
    implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String age;

    @Column(nullable = false)
    private String country;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "author")
    private Set<Book> books;

    public Author(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Author(Integer id, String name, String age, String country) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.country = country;
    }

}
