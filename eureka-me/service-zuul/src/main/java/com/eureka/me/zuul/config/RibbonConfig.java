package com.eureka.me.zuul.config;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.*;

@Configuration
public class RibbonConfig {

    @Bean
    public IRule ribbonIRule(){
        return new BestAvailableRule();
    }
}
