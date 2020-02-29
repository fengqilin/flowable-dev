package com.joywayi.service;

import com.joywayi.flowable.FlowBroker;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HolidayService extends FlowBroker {
    @Override
    public Map<String, Object> getBizParams() {
        HashMap<String,Object> map = new HashMap<>();
        map.put("days",10);
        return  map;
    }
}
