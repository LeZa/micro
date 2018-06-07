package com.eureka.me.client.controller;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Enumeration;

@RestController
public class HelloController {


    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

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

    @GetMapping( value = "/udateUser" )
    public String updateUser(){
        this.loadBalancerClient.choose("service-token");
        String resultStr = this.restTemplate.getForObject("http://service-token/update",String.class);
        return  resultStr;
    }

}
