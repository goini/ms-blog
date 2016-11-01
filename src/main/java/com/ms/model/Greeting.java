package com.ms.model;

import org.springframework.stereotype.Component;

/**
 * Created by mshemet on 31/10/2016.
 */
@Component(value = "empty")
public class Greeting {
    private long id;
    private String content;

    public Greeting() {
    }

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Greeting t(long id, String content) {
        this.id = id;
        this.content = content;
        return this;
    }
}
