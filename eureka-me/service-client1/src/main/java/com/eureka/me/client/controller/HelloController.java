package com.eureka.me.client.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {


    @Autowired
    private  JdbcTemplate jdbcTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate  restTemplate;


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
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from sl_user");
        Gson gson = new Gson();
        return   gson.toJson(list);
    }

    @GetMapping( value = "/updateUser" )
    public String updateUser(){
        ServiceInstance serviceInstance =  this.loadBalancerClient.choose("service-token");
      System.out.println( "...."+  serviceInstance.getServiceId());
        System.out.println( "...."+ serviceInstance.getUri());
        System.out.println( "...."+  serviceInstance.getHost());
        String resultStr = this.restTemplate.getForObject("http://service-token/update",String.class);
        return resultStr;
    }
}
