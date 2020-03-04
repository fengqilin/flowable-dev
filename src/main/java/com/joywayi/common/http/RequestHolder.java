package com.joywayi.common.http;

import java.util.HashMap;
import java.util.Map;

public class RequestHolder {
    private final static ThreadLocal<Map<String, Object>> holder = ThreadLocal.withInitial(() -> new  HashMap<>() );

    public static Map<String, Object> getParams() {
        return holder.get();
    }

    public static void setValue(String key, Object value) {
        getParams().put(key, value);
    }

    public static void remove() {
        holder.remove();
    }
}
