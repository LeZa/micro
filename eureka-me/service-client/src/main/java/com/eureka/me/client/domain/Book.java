package com.eureka.me.client.domain;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Book
    implements Serializable{

    private static final long serialVersionUID = 8271793387630052314L;

    @Id
    private Integer id;

    @Column(nullable = false)
    @NaturalId
    private String name;

    @ManyToOne(optional = false)
    @NaturalId
    private  Author author;

    public Book() {
    }



    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                '}';
    }
}
