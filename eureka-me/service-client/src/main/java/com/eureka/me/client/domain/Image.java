package com.eureka.me.client.domain;


import com.eureka.me.client.config.JsonKey;
import com.google.gson.annotations.Expose;
import zipkin.internal.moshi.Json;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Image
    implements Serializable{

    private static final long serialVersionUID = -2941878342840924527L;


    public Image() {
    }

    @Expose
    @Id
    private Integer id;

    @Expose
    @Column(nullable = false)
    private String path;

    @ManyToOne(optional =  false)
    private Teacher teacher;

    @JsonKey(value="id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonKey(value ="path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @JsonKey( value="teacher",notNull = true)
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
