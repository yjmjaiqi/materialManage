package com.example.yjm.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    /**
     * 自己定义了一个redisTemplate
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 为了自己开发方便，一般使用<String,Object>
        RedisTemplate<String, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        // json的序列化配置
        GenericFastJsonRedisSerializer serializer = new GenericFastJsonRedisSerializer();
        template.setDefaultSerializer(serializer);

        // string的序列化配置
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用string序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用string的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);

        // value采用FastJson的序列化方式
        template.setValueSerializer(serializer);
        // hash的value也采用FastJson的序列化方式
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
}
