package com.eureka.me.zuul.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;

@Configuration
@EnableCaching
public class RedisConfig {


    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private int maxWait;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.password}")
    private String password;

/*
    @Value("${spring.redis.database}")
    private int database;
    @Value("${spring.redis.pool.min-idle}")
    private int minIdle;
*/

    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    @Bean
    public CacheManager cacheManager() {

//设置缓存过期时间
//rcm.setDefaultExpiration(60);//秒

        RedisCacheManager rcm  = RedisCacheManager.create( redisConnectionFactory());
        return rcm;
    }


    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory( redisConnectionFactory());
        //key序列化方式;但是如果方法上有Long等非String类型的话，会报类型转换错误；
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();//Long类型不可以会出现异常信息;
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);

        //JdkSerializationRedisSerializer序列化方式;
        JdkSerializationRedisSerializer jdkRedisSerializer=new JdkSerializationRedisSerializer();
        redisTemplate.setValueSerializer(jdkRedisSerializer);
        redisTemplate.setHashValueSerializer(jdkRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    @Bean
    public JedisConnectionFactory redisConnectionFactory() {

        RedisStandaloneConfiguration redisStandaloneConfiguration
                = new RedisStandaloneConfiguration(host,port);

        RedisPassword redisPassword = RedisPassword.of(password.toCharArray());
        redisStandaloneConfiguration.setPassword(redisPassword);

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisPoolConfig.setMaxIdle( maxIdle );
        jedisPoolConfig.setMaxWaitMillis( maxWait);

        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.defaultConfiguration();

        JedisConnectionFactory factory = new JedisConnectionFactory( redisStandaloneConfiguration,jedisClientConfiguration);
       /* JedisClientConfiguration.DefaultJedisClientConfigurationBuilder jedisClientConfiguration
                 =  new JedisClientConfiguration.DefaultJedisClientConfigurationBuilder().clientName()
                    .builder();*/
     /*   JedisPoo
        factory.setHostName(host);
        factory.setPort(port);
        factory.setPassword(password);
        //存储的库
        factory.setDatabase(database);
        //设置连接超时时间
        factory.setTimeout(timeout);
        factory.setUsePool(true);
        factory.setPoolConfig(jedisPoolConfig());*/
        return factory;
    }



}
