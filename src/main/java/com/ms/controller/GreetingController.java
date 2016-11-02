package com.ms.controller;

import com.ms.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by mshemet on 31/10/2016.
 */
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    @Qualifier("empty")
    private Greeting greeting;

    @Autowired
    @Qualifier("inited")
    private Greeting greeting2;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return greeting.t(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/greeting/{book}/{page}/dfgfd")
    public Integer greeting(@PathVariable(value="id") Integer i) {
        return i++;
    }
}
