package com.eureka.me.client.controller;

import com.eureka.me.client.config.UserTypeAdapter;
import com.eureka.me.client.domain.Student;
import com.eureka.me.client.domain.Teacher;
import com.eureka.me.client.repository.TeacherRepository;
import com.eureka.me.client.service.AuthorService;
import com.eureka.me.client.service.BookService;
import com.eureka.me.client.service.StudentService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@EnableEurekaClient
@RestController
public class HelloController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentService studentService;


    @Transactional(readOnly = true)
    public String hi(@RequestParam String name) throws SQLException {

        StringBuilder sb = new StringBuilder(1024).append("[");
        List<Teacher> resultList = teacherRepository.findAll();
        Gson gson = new GsonBuilder()
               .registerTypeAdapter(Teacher.class,new UserTypeAdapter())
               .setPrettyPrinting()
               .disableHtmlEscaping()
                .create();
        for( Teacher teacher : resultList ){
           sb.append(
                   gson.toJson( teacher ).replaceAll("\\\\","")
           ).append(",");
        }
        String sbVal =  sb.substring(0,sb.length()-1);
        sbVal = sbVal + "]";
        Page<Student> pageResult = studentService.selectAllStudent();
        System.out.println( "...."+pageResult.getContent());

        return  sbVal;
//         return bookService.getBook(author,"thinking in java").toString();
//        return authorService.findAll(null,new PageableConfig(1,1)).toString();
    }




    @GetMapping(value ="hi1")
    public String gethi1(){
            return "hi1";
    }
}
