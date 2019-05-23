package com.im.zemel.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceUtil {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    public void setKey(String key, String value) {
        stringRedisTemplate.delete(key);
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set(key, value);

    }

    public void setKey(String key, TimeUnit time, int count) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String value = ops.get(key);
        if (value != null) {
            stringRedisTemplate.delete(key);
            ops.set(key, value, count, time);
        }
    }

    public String getValue(String key) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        return ops.get(key);
    }

    public void deleteKey(String key) {
        stringRedisTemplate.delete(key);
    }

    public boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    public void setKey(String key) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String value = ops.get(key);
        stringRedisTemplate.delete(key);
        ops.set(key, value);
    }
}
