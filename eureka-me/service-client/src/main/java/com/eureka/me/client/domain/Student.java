package com.eureka.me.client.domain;


import com.eureka.me.client.config.JsonKey;
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Student extends Base
    implements Serializable{

    private static final long serialVersionUID = -7207801345607560106L;

    @Expose
    @Id
    private Integer id;

    @Expose
    @Column(nullable =  false)
    private String name;

    @Expose
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "students")
    private List<Teacher> teachers;

    @JsonKey(value = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonKey(value="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonKey(value="teacher",isCollection = true)
    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
