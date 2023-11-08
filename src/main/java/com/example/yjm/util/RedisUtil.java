package com.example.yjm.util;

import com.example.yjm.entity.LoginUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void SaveUser(StringRedisTemplate stringRedisTemplate, LoginUser user, String token) throws JsonProcessingException {
        // 手动序列化
        String json = mapper.writeValueAsString(user);
        // 写入数据
        String id = JWTUtil.getMsg(token);
        stringRedisTemplate.opsForValue().setIfAbsent("loginId:" + id, json,3000, TimeUnit.MINUTES);

    }
    public static LoginUser getUser(StringRedisTemplate stringRedisTemplate,String token) throws JsonProcessingException {

        String id = JWTUtil.getMsg(token);
        // 获取数据
        String jsonUser = stringRedisTemplate.opsForValue().get("loginId:" + id);
        // 手动反序列化

        LoginUser user = mapper.readValue(jsonUser, LoginUser.class);
        System.out.println("user = " + user);
        return user;
    }
}
