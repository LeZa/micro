package com.eureka.me.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Enumeration;

@RestController
public class HelloController {

    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name, HttpServletRequest request ) throws SQLException {
        /**
         * @Description
         */
        String username = request.getHeader("username");
        Enumeration<String> headerNames = request.getHeaderNames();
        while( headerNames.hasMoreElements()){
            String nextElementStr = headerNames.nextElement();
            System.out.println(nextElementStr+":"+request.getHeader( nextElementStr ));
        }
        return  "hi"+username+" this is 89.54 client1";
    }

}
