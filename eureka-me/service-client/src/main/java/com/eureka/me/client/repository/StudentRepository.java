package com.eureka.me.client.repository;


import com.eureka.me.client.domain.Student;
import com.eureka.me.client.service.StudentSService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;


public interface StudentRepository
    extends Repository<Student,Long> {

    @Query("select t.id as id,t.name as name from Student t")
    Page<Student> selectStudent(Pageable pageable);

}
