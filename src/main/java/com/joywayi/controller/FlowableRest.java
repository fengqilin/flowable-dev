package com.joywayi.controller;

import com.joywayi.common.http.HttpResult;
import com.sun.xml.internal.xsom.impl.scd.Iterators;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ManagementService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityManager;
import org.flowable.engine.impl.util.CommandContextUtil;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/job")
public class FlowableRest {
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    IdentityService identityService;
    @Autowired
    TaskService taskService;
    @Autowired
    ManagementService managementService;

    /**
     * 启动，假定用户就是 100
     *
     * @param processKey
     * @return
     */

    @RequestMapping(value = "/start/{processKey}", method = RequestMethod.GET)
    public HttpResult start(@PathVariable(name = "processKey") String processKey) {
        identityService.setAuthenticatedUserId("100");
        //启动一个新流程
        String procId = runtimeService.startProcessInstanceByKey(processKey).getId();
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(procId).list();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId( procId  ).singleResult();
        System.out.println("---------------");
        System.out.println(processInstance);

        for (Task task : taskList) {
            System.out.println(task.getId());
            System.out.println(task.getFormKey());

            //完成第一节点
            Map<String, Object> map = new HashMap<>();
            map.put("assigneeList", Arrays.asList("101", "102", "103"));
            map.put("chiefApprovePassed", false);
            taskService.complete(task.getId(), map);
            //加一个节点
            runtimeService.addMultiInstanceExecution("Approve", task.getProcessInstanceId(), Collections.singletonMap("assignee", "104"));
            //串行节要在人员里加入上一句调用加入的人员， 或是一句把这个修改后的参数放入， 不知道算不算bug
            //加 104
            List obj = (List) runtimeService.getVariable(task.getProcessInstanceId(), "assigneeList");
            List list = new ArrayList(obj);
            list.add("104");
            runtimeService.setVariable(task.getProcessInstanceId(), "assigneeList", list);

            //加105
//            runtimeService.addMultiInstanceExecution("Approve", task.getProcessInstanceId(), Collections.singletonMap("assignee", "105"));
//            obj = (List) runtimeService.getVariable(task.getProcessInstanceId(), "assigneeList");
//            list = new ArrayList(obj);
//            list.add("105");
//            runtimeService.setVariable(task.getProcessInstanceId(), "assigneeList", list);


        }
        return HttpResult.ok("procId:" + procId);
    }

    /**
     * 串行多实例的一个测试 next --->
     */


    @RequestMapping(value = "/{procId}/next", method = RequestMethod.GET)
    public HttpResult next(@PathVariable(name = "procId") String procId) {
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(procId).list();

        String result = "";
        for (Task task : taskList) {
            System.out.println(task.getId());
            System.out.println(task.getFormKey());

            Integer nrOfInstances = (Integer) taskService.getVariable(task.getId(), "nrOfInstances");
            Integer nrOfCompletedInstances = (Integer) taskService.getVariable(task.getId(), "nrOfCompletedInstances");
            result += task.getId()  + "===" + task.getAssignee() + "===>"  + "--->" + nrOfCompletedInstances + "/" + nrOfInstances;
            taskService.complete(task.getId());




            //后加---106
//            runtimeService.addMultiInstanceExecution("Approve", task.getProcessInstanceId(), Collections.singletonMap("assignee", "106"));
//
//            List obj = (List) runtimeService.getVariable(task.getProcessInstanceId(), "assigneeList");
//            List list = new ArrayList(obj);
//            list.add("106");
//            runtimeService.setVariable(task.getProcessInstanceId(), "assigneeList", list);





        }
        return HttpResult.ok(result);
    }
}
