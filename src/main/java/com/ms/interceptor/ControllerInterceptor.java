package com.ms.interceptor;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
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

    private static Logger log = Logger.getLogger(ControllerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug(String.format("{%s-pre} - %s", request.getMethod(), request.getRequestURI()));
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug(String.format("{%s-post} - %s", request.getMethod(), request.getRequestURI()));
        super.postHandle(request, response, handler, modelAndView);
    }
}
