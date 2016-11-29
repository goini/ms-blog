package com.ms.interceptor;

<<<<<<< HEAD
=======
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
>>>>>>> refs/remotes/maksymshemet/master
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mshemet on 10/11/2016.
 */
@Component
public class ControllerInterceptor extends HandlerInterceptorAdapter {

<<<<<<< HEAD
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getMethod() + " before request: " + request.getRequestURI());
=======
    private static Logger log = Logger.getLogger(ControllerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug(String.format("{%s-pre} - %s", request.getMethod(), request.getRequestURI()));
>>>>>>> refs/remotes/maksymshemet/master
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
<<<<<<< HEAD
        System.out.println(request.getMethod() + " after request: " + request.getRequestURI());
=======
        log.debug(String.format("{%s-post} - %s", request.getMethod(), request.getRequestURI()));
>>>>>>> refs/remotes/maksymshemet/master
        super.postHandle(request, response, handler, modelAndView);
    }
}
