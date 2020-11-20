package com.example.supermarket.utils;

import com.example.supermarket.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(AdminInterceptor.class);
    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("执行了AdminInterceptor的preHandle方法");
        //统一拦截（查询当前session是否存在user）(这里user会在每次登陆成功后，写入session)
        User user=(User)request.getSession().getAttribute("USER");
        if(user != null){
            System.out.println("A user has logged.");
            return true;
        }
        System.out.println("this page has been redirected again!");
//      response.sendRedirect(request.getContextPath()+"/login.html");
        return true;//如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
        //如果设置为true时，请求将会继续执行后面的操作
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        logger.info("执行了AdminInterceptor的postHandle方法");
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.info("执行了AdminInterceptor的afterCompletion方法");

    }
}
