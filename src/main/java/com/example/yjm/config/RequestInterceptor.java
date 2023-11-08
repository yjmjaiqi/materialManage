// package com.example.yjm.config;
//
// import com.example.yjm.util.JWTUtil;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.stereotype.Component;
// import org.springframework.web.servlet.HandlerInterceptor;
// import org.springframework.web.servlet.ModelAndView;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
//
// @Configuration
// public class RequestInterceptor implements HandlerInterceptor {
//
//     private static final String[] ALLOWED_PATHS = {
//             "/material/login", "/material/register", "/material/findPass", "/material/sendEmail"
//     };
//
//
//     @Override
//     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//         String requestPath = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
//         // 检查请求路径是否在白名单中
//         System.out.println("鉴权------------");
//         for (String path : ALLOWED_PATHS) {
//             if (requestPath.startsWith(path)) {
//                 System.out.println("白名单放行:" + path);
//                 // 在白名单中，放行
//                 return true;
//             }
//         }
//         System.out.println(requestPath);
//
//         // 从请求头中获取 token
//         String token = request.getHeader("");
//         System.out.println(token);
//
//         // 验证 token 的合法性
//         if (JWTUtil.verify(token)) {
//             return true;  // 放行
//         } else {
//             // Token 不合法，返回未授权的响应
//             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//             return false;
//         }
//     }
//     @Override
//     public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//         // 在请求处理后但在视图渲染前执行
//     }
//
//     @Override
//     public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//         // 在请求完成后执行，包括视图渲染完成后
//     }
// }
