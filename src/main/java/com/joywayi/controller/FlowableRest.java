package com.joywayi.controller;

import com.joywayi.common.http.HttpResult;
import com.joywayi.common.http.RequestHolder;
import com.joywayi.dao.ApproveConfigDao;
import com.joywayi.process.ApproveConfig;
import com.joywayi.process.QApproveConfig;
import com.joywayi.service.EvalucateExpression;
import com.querydsl.core.types.Predicate;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ManagementService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
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

    @Autowired
    EvalucateExpression evalucateExpression;

    @Autowired
    ApproveConfigDao approveConfigDao;


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
        Map<String, Object> map = new HashMap<>(2);
        map.put("assigneeList", new ArrayList<String>(1));
        map.put("chiefApprovePassed", false);

        String procId = runtimeService.startProcessInstanceByKey(processKey, map).getId();
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(procId).list();
//        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(procId).singleResult();

        for (Task task : taskList) {
            System.out.println(task.getId());
            System.out.println(task.getFormKey());

            //完成第一节点
//            Map<String, Object> map = new HashMap<>();
//            map.put("assigneeList", Arrays.asList("101", "102", "103"));
//            map.put("chiefApprovePassed", false);
//            taskService.complete(task.getId(), map);
            //加一个节点
//            runtimeService.addMultiInstanceExecution("Approve", task.getProcessInstanceId(), Collections.singletonMap("assignee", "104"));
            //串行节要在人员里加入上一句调用加入的人员， 或是一句把这个修改后的参数放入， 不知道算不算bug
            //加 104
//            List obj = (List) runtimeService.getVariable(task.getProcessInstanceId(), "assigneeList");
//            List list = new ArrayList(obj);
//            list.add("104");
//            runtimeService.setVariable(task.getProcessInstanceId(), "assigneeList", list);

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


    @RequestMapping(value = "/{procId}/complete", method = RequestMethod.GET)
    public HttpResult next(@PathVariable(name = "procId") String procId) {
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(procId).list();

        //收集表单数据 通过threadlocal传递到 监听事件中做对应处理

        String result = "";
        for (Task task : taskList) {
            System.out.println(task.getId());
            System.out.println(task.getFormKey());
            Integer nrOfInstances = (Integer) taskService.getVariable(task.getId(), "nrOfInstances");
            Integer nrOfCompletedInstances = (Integer) taskService.getVariable(task.getId(), "nrOfCompletedInstances");
            result += task.getId() + "===" + task.getAssignee() + "===>" + "--->" + nrOfCompletedInstances + "/" + nrOfInstances;

            taskService.complete(task.getId());

//            runtimeService.createChangeActivityStateBuilder().processInstanceId().changeState();


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

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public HttpResult test() {
        evalucateExpression.evalcate();
        ApproveConfig config = new ApproveConfig();
        config.setName("测试二");
        config.setProcessKey("holidayRequest");
        config.setTaskKey("Approve");
        approveConfigDao.save(config);

        QApproveConfig qConfig = QApproveConfig.approveConfig;
        Predicate predicate = qConfig.name.eq("测试一");

        Iterable list = approveConfigDao.findAll(predicate);
        return HttpResult.ok(list);

    }


}
