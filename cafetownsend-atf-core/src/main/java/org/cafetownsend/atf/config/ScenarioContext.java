package org.cafetownsend.atf.config;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ScenarioContext {

    private Map<Object, Object> data = new HashMap<>();

    public void save(Object key, Object value) {
        this.data.put(key, value);
    }

    public Object getData(Object key) {
        return this.data.get(key);
    }
}
