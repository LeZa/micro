package com.eureka.me.client.domain;


import com.eureka.me.client.config.JsonKey;
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Teacher extends Base
    implements Serializable{

    private static final long serialVersionUID = -1383384909413378947L;

    @Expose
    @Id
    private Integer id;

    @Expose
    @Column( nullable =  false)
    private String name;

    @Expose
    @ManyToMany
    private List<Student> students;

    @Expose
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher")
    private List<Image> images;

    @JsonKey(value="id")
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

    @JsonKey(value = "student",isCollection = true)
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @JsonKey(value="image",isCollection = true)
    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
