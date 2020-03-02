package com.joywayi.controller.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.joywayi.common.http.HttpResult;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.Process;
import org.flowable.bpmn.model.UserTask;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngines;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ModelQuery;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/process")
public class FlowableManager {
    @Autowired
    ProcessEngine processEngine;
    @Autowired
    RepositoryService repositoryService;

    @RequestMapping("/deployment")
    public  HttpResult deployment() throws FileNotFoundException {
        InputStream in = new FileInputStream("/Users/feng/eclipse-workspace/bpmn/src/holiday.bpmn");
//                name must end with  bpmn20.xml or bpmn ,
//                 if not you can deploy success,but you can not see model in act_re_procdef
        Deployment deployment = repositoryService.createDeployment().addInputStream("bpmn",in).deploy();

//
//
        //获取流程节点
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition processDefinition : list) {
            System.out.println(processDefinition.getId());
            System.out.println(processDefinition.getName());
            System.out.println(processDefinition.getResourceName());
            List<Process> processList = repositoryService.getBpmnModel(processDefinition.getId()).getProcesses();
            for (Process process : processList) {
                Collection<FlowElement> flowElements = process.getFlowElements();
                for (FlowElement flowElement : flowElements) {
                    System.out.println( flowElement.getClass());
                    if (flowElement instanceof UserTask) {
                        System.out.println("UserTask：" + flowElement.getName() + ":" + ((UserTask) flowElement).getId() );
                        System.out.println("UserTask：" + ((UserTask) flowElement).getTaskListeners());

                    }
                }
            }
        }
        return  HttpResult.ok(deployment.getId());
    }

    /**
     * 获取发布的流程
     * 直接写，还没有分层封装
     * @return
     */
    @RequestMapping("/list")
    public HttpResult getProcessList(){
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().latestVersion().list();
        JSONArray jsonArray = new JSONArray();
        for( ProcessDefinition processDefinition : list  ){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("description",processDefinition.getDescription());
            jsonObject.put("name",processDefinition.getName());
            jsonObject.put("id",processDefinition.getId());
            jsonObject.put("key",processDefinition.getKey());
            jsonObject.put("deploymentId",processDefinition.getDeploymentId());
            jsonObject.put("deploymentId",processDefinition.getDeploymentId());
            jsonArray.add(jsonObject);
        }
        return  HttpResult.ok(jsonArray);
    }

    @RequestMapping("/processDefinition/processes/{processDefinitionId}")
    public HttpResult processes(@PathVariable("processDefinitionId") String  processDefinitionId){
        List<Process> processList = repositoryService.getBpmnModel( processDefinitionId ).getProcesses();
        JSONArray jsonArray = new JSONArray();

        for (Process process : processList) {
            Collection<FlowElement> flowElements = process.getFlowElements();
            for (FlowElement flowElement : flowElements) {
//                System.out.println( flowElement.getClass());
                if (flowElement instanceof UserTask) {
                    JSONObject jsonObject = new JSONObject();
                    System.out.println("UserTask：" + flowElement.getName() + ":" + ((UserTask) flowElement).getId() );
                    System.out.println("UserTask：" + ((UserTask) flowElement).getTaskListeners());
                    jsonObject.put("name",flowElement.getName());
                    jsonObject.put("id",flowElement.getId());
                    jsonArray.add(jsonObject);
                }
            }
        }
        return  HttpResult.ok(jsonArray);
    }

    /**
     * 获取流程流转的条件配置
     * @param pid 流程ID
     * @return
     */
    @RequestMapping("/getCondition")
    public HttpResult getFlowCondition(@RequestParam("pId") String  pid){

        return  HttpResult.ok();

    }
    @RequestMapping(value = "/saveConditions",method = RequestMethod.POST)
    public HttpResult saveFlowCondition() {
        return  HttpResult.ok();
    }




}
