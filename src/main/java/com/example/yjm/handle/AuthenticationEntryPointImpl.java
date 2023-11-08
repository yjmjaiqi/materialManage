package com.example.yjm.handle;

import com.alibaba.fastjson.JSON;
import com.example.yjm.util.Result;
import com.example.yjm.util.WebUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    // 权限认证
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result<String> authority = Result.authority("用户认证失败请重新登录");
        String json = JSON.toJSONString(authority);
        WebUtil.print(response,json);
    }
}
