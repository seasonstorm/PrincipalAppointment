package com.cuit.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String requestUrl = httpServletRequest.getRequestURI().replace(httpServletRequest.getContextPath(), "");

        HttpSession session = httpServletRequest.getSession();
        Object object = session.getAttribute("user");
        if (object == null || object.toString().equals("")) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/headteacher/content/main.html");
            System.out.println("拦截到的请求地址是" + requestUrl + "：未登录已转向主页面");
            return false;
        }
        System.out.println("拦截到的请求地址是" + requestUrl + "：已登录通过检查不处理");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
