package com.lesson.warehouse.domain;

import java.util.Map;

public class Subscriber {

    public interface Callback {
        void execute(Map<String, Object> model);
    }

    private String name;
    private Callback callback;

    public Subscriber(String name, Callback callback) {
        this.name = name;
        this.callback = callback;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
