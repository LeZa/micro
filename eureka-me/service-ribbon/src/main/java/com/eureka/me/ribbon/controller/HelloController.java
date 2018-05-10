package com.eureka.me.ribbon.controller;

import com.eureka.me.ribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
/*import org.springframework.security.access.prepost.PreAuthorize;*/
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name,HttpServletRequest request){

      /*  String access_token = request.getParameter("access_token");*/
        this.loadBalancerClient.choose("service-client");
//        String resultStr = restTemplate.getForObject("http://service-client/hi?name="+name+"&access_token="+access_token,String.class);
        String resultStr = restTemplate.getForObject("http://service-client/hi?name="+name,String.class);
        return resultStr;
    }


    @RequestMapping(value = "/addDemo")
    public String addDemo(HttpServletRequest request){
        String access_token = request.getParameter("access_token");
        this.loadBalancerClient.choose("service-client");
        String resultStr = restTemplate.getForObject("http://service-client/addDemo?access_token="+access_token,String.class);
        return resultStr;
    }

    @GetMapping(value = "/hi1")
    public String hiHystrix(@RequestParam String name){
        return "static";
    }

}
