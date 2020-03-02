package com.joywayi.common.http;

import java.util.Map;

public class RequestHolder {

    private final static ThreadLocal<Map<String, Object>> holder = new ThreadLocal<>();

    public static Map<String, Object> getParams() {
        return holder.get();
    }

    public static void setValue(String key, Object value) {
        holder.get().put(key, value);
    }

    public static void remove() {
        holder.remove();
    }
}
