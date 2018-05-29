package com.eureka.build.oauth.common;

import org.springframework.dao.*;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.jedis.*;
import redis.clients.jedis.*;

public class RedisClientFactory
                implements RedisConnectionFactory {
    @Override
    public RedisConnection getConnection() {
        Jedis jedis =  new Jedis("127.0.0.1",6379);
        jedis.auth("123456");
        JedisConnection jedisConnection =  new JedisConnection(jedis);
         DefaultStringRedisConnection defaultStringRedisConnection = new DefaultStringRedisConnection(jedisConnection);
        return  defaultStringRedisConnection;
    }

    @Override
    public RedisClusterConnection getClusterConnection() {
        return null;
    }

    @Override
    public boolean getConvertPipelineAndTxResults() {
        return false;
    }

    @Override
    public RedisSentinelConnection getSentinelConnection() {
        return null;
    }

    @Override
    public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
        return null;
    }
}
