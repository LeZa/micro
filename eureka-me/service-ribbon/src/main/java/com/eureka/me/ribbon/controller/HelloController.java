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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

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

//        request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
//                .getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        while( headerNames.hasMoreElements()){
            String nextElementStr = headerNames.nextElement();
            System.out.println(nextElementStr+":"+request.getHeader( nextElementStr ));
        }


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
