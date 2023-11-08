package com.example.yjm.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.yjm.dao.UserMapper;
import com.example.yjm.entity.LoginUser;
import com.example.yjm.entity.User;
import com.example.yjm.util.JWTUtil;
import com.example.yjm.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Objects;

@Component
public class JwtFilter extends OncePerRequestFilter {
    // @Autowired
    // private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");


        if(!StringUtils.hasText(token)){
            filterChain.doFilter(request,response);
            return;
        }
        System.out.println(token);
        // LoginUser loginUser = RedisUtil.getUser(stringRedisTemplate, token);
        LoginUser loginUser = (LoginUser) redisTemplate.opsForValue().get("loginId:" + JWTUtil.getMsg(token));
        if(Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);
        return;

    }
}
