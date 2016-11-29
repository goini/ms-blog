package com.ms.configuration;

import com.ms.interceptor.ControllerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by mshemet on 10/11/2016.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private ControllerInterceptor interceptor;

    @Autowired
    public WebMvcConfig(ControllerInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }
}
