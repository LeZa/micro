package com.eureka.me.zuul.config;


import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfig {

/*    @Bean
    public IRule iRule(){
        return new BestAvailableRule();
    }*/

    @Bean
    public IRule iRule(){
        return new ZoneAvoidanceRule();
    }

/*  @Bean
    public IRule iRule(){
      return new RoundRobinRule();
  }*/
}
