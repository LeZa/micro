package com.eureka.me.client.repository;

import com.eureka.me.client.domain.Teacher;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaTeacherRepository
    implements  TeacherRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Teacher> findAll() {
        return  this.entityManager.createQuery("SELECT n FROM Teacher n ",Teacher.class).getResultList();
    }
}
