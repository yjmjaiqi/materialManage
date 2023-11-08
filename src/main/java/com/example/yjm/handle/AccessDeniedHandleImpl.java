package com.example.yjm.handle;

import com.alibaba.fastjson.JSON;
import com.example.yjm.util.Result;
import com.example.yjm.util.WebUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandleImpl implements AccessDeniedHandler {
    // 异常处理
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result<String> authority = Result.authority("权限不足");
        String json = JSON.toJSONString(authority);
        WebUtil.print(response,json);
    }
}
