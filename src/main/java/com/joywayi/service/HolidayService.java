package com.joywayi.service;

import com.joywayi.flowable.FlowBroker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class HolidayService extends FlowBroker {

     private Logger logger = LoggerFactory.getLogger(HolidayService.class);

    @Override
    public Map<String, Object> getBizParams() {

        HashMap<String,Object> map = new HashMap<>();
        map.put("days",new Random().nextInt(100));
        return  map;
    }

    @Override
    public void saveBizData(Map<String, Object> formParams) {
        logger.info("参数为：" + formParams);
    }

    @Override
    public void nextCandidate() {
         logger.info("下一节点处理人，用于复杂流程");
    }

    public String test(String abc){
        return    System.currentTimeMillis()  + "=---" +abc;
    }
}
