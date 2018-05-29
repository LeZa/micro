package com.eureka.build.oauth.config;

import com.eureka.build.oauth.common.*;
import org.springframework.context.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.data.repository.query.*;
import org.springframework.security.oauth2.provider.token.*;

@Configuration
public class BeanConfig {

    @Primary
    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setAccessTokenValiditySeconds(60*60*24*10);
        defaultTokenServices.setRefreshTokenValiditySeconds(60*60*24*15);
        defaultTokenServices.setSupportRefreshToken(false);
        defaultTokenServices.setReuseRefreshToken(false);
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new DomainUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

    @Bean
    public RedisTokenStoreCopy tokenStore() {
        return new RedisTokenStoreCopy(new RedisClientFactory());
    }

}
