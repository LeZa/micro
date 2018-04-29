package com.eureka.me.client.service;

import com.eureka.me.client.domain.Student;
import com.eureka.me.client.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component("studentService")
public class StudentServiceImpl
    implements StudentService{

    private final  StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Page<Student> selectAllStudent() {

        return this.studentRepository.selectStudent(new PageRequest(0,2));
    }
}
