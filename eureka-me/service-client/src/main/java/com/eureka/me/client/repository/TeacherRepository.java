package com.eureka.me.client.repository;

import com.eureka.me.client.domain.Teacher;

import java.util.List;

public interface TeacherRepository {

    List<Teacher> findAll();
}
