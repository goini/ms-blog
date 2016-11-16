package com.ms.configuration;

import com.ms.interceptor.ControllerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.*;

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
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/error")
                .setStatusCode(HttpStatus.BAD_REQUEST)
                .setViewName("error.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        super.configurePathMatch(configurer);
        configurer.setUseSuffixPatternMatch(false);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }


}
