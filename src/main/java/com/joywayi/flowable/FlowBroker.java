package com.joywayi.flowable;


import com.joywayi.common.http.RequestHolder;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.flowable.task.service.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;


/**
 * 流程通用法
 */
public abstract class FlowBroker implements TaskListener, ExecutionListener {

    @Autowired
    RuntimeService runtimeService;
    @Autowired
    RepositoryService repositoryService;

    protected static final String TASK_CREATE = "create";
    protected static final String TASK_COMPLETE = "complete";
    protected static final String TASK_APPLY_ID = "Apply";
    protected static final String TASK_APPROVE_ID = "Approve";


    /**
     * 用户任务   create, assignment,complete ,all
     *
     * @param delegateTask
     */
    @Override
    public void notify(DelegateTask delegateTask) {

        //  apply 节点 complete事件
        if (TASK_APPLY_ID.equals(delegateTask.getTaskDefinitionKey()) && TASK_COMPLETE.equals(delegateTask.getEventName())) {
            System.out.println("apply 节点   complete事件，根据规则，决定下一个审批节点人");
            //保存业务数据
            this.saveBizData(RequestHolder.getParams());
            //获取业务属性 一大堆条件判断
            Map<String, Object> auditElements = this.getBizParams();
            Integer days = (Integer) auditElements.get("days");
            if(days % 3 == 0){
                runtimeService.setVariable(delegateTask.getProcessInstanceId(), "assigneeList", Arrays.asList("33333"));
            }else {
                runtimeService.setVariable(delegateTask.getProcessInstanceId(), "assigneeList", Arrays.asList("11111"));
            }
        }

        System.out.println(delegateTask.getName() + " DelegateTask ===>" + delegateTask.getId() + ">>>" + delegateTask.getEventName());
    }


    /**
     * 全局 start  end 事件
     *
     * @param delegateExecution
     */
    @Override
    public void notify(DelegateExecution delegateExecution) {
        System.out.println("DelegateExecution >>> " + delegateExecution.getEventName());

    }

    public abstract Map<String, Object> getBizParams();

    public abstract void saveBizData(Map<String, Object> formParams);

    public  void nextCandidate(){

    }
}
