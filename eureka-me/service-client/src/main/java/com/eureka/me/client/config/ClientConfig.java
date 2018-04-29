package com.eureka.me.client.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ClientConfig {

    @Bean
    @ConfigurationProperties(prefix="jdbc.datasource")
    public  DataSource getDataSource(){
            /*return new FancyDataSourceProperties();*/
            return DataSourceBuilder.create().build();
    }


}
