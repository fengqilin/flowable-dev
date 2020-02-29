package com.joywayi.flowable;


import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.flowable.task.service.delegate.TaskListener;

import java.util.HashMap;
import java.util.Map;

/**
 * 流程通用法
 */
public  abstract class FlowBroker implements TaskListener, ExecutionListener {


    /**
     * 用户任务   create, assignment,complete ,all
     * @param delegateTask
     */
    @Override
    public void notify(DelegateTask delegateTask) {

        System.out.println( delegateTask.getName() + " DelegateTask ===>"  + delegateTask.getId() + ">>>"  +  delegateTask.getEventName()    );
    }

    /**
     * 全局 start  end 事件
     * @param delegateExecution
     */
    @Override
    public void notify(DelegateExecution delegateExecution) {
        System.out.println(  "DelegateExecution >>> " +  delegateExecution.getEventName() );

    }

    public  abstract Map<String,Object> getBizParams() ;
}
