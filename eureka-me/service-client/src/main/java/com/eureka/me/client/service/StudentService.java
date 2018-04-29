package com.eureka.me.client.service;

import com.eureka.me.client.domain.Student;
import org.springframework.data.domain.Page;

public interface StudentService {

    Page<Student> selectAllStudent();
}
