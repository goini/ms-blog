package com.ms.configuration;

import com.ms.model.Greeting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by mshemet on 31/10/2016.
 */
@Configuration
public class TestConfig {

    @Bean(name = "inited")
    @Scope("prototype")
    public Greeting greeting() {
        return new Greeting(1, "max");
    }
}
