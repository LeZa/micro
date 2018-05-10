package com.eureka.me.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class HelloController {

    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name) throws SQLException {
        return  "hi client1 7863";
    }

}
