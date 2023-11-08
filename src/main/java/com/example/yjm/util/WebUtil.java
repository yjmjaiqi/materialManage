package com.example.yjm.util;


import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class WebUtil {
    public static final String USER_INFO = "userInfo";
    public static final String USER_NAME = "username";
    public static final String NEED_LOGIN = "needLogin";

    /**
     * Obtain ServletRequest header value
     *
     * @param request
     * @param name
     * @return
     */
    public static String getHeaderValue(HttpServletRequest request, String name) {
        String v = request.getHeader(name);
        if (v == null) {
            return null;
        }
        return v.trim();
    }


    public static void redirect(HttpServletResponse response, HttpServletRequest request, String path) throws IOException {
        response.sendRedirect(request.getContextPath() + path);
    }

    /**
     * Obtain the full url path
     *
     * @param request
     * @return
     */
    public static String getUrl(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String queryString = request.getQueryString();
        if (queryString != null) {
            url += "?" + request.getQueryString();
        }
        return url;
    }

    /**
     * Write content to front-page/response
     *
     * @param response
     * @param result
     * @throws IOException
     */
    public static void print(HttpServletResponse response, String result) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }

    public static Object getValueFromSession(HttpServletRequest request, String key) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            return  session.getAttribute(key);
        }

        return null;
    }



    public static void removeSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    }

    public static void setSessionValue(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession();
        session.setAttribute(key, value);
    }

    public static String getSessionId(HttpServletRequest request) {
        return request.getSession().getId();
    }
}

