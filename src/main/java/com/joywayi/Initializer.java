package com.joywayi;

import org.flowable.engine.IdentityService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.transaction.Transactional;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = Initializer.class)
@ServletComponentScan(basePackageClasses = Initializer.class ) //filter
public class Initializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(getClass());
    }












    @Bean
    public CommandLineRunner init(final RepositoryService repositoryService,
                                  final RuntimeService runtimeService,
                                  final TaskService taskService,
                                  final IdentityService identityService

    ) {

        return new CommandLineRunner() {
            @Override
            @Transactional
            public void run(String... strings) throws Exception {
                //启动前设置用户 100 发起， 101,102,103顺序 审 发起没有指定审批人
//                identityService.setAuthenticatedUserId("100");
//                ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holidayRequest");


//                List<Task> taskList = taskService.createTaskQuery().taskAssignee("10001").list();
//                for(Task task :taskList ){
//                    System.out.println("  " + task.getId());
//                    System.out.println("  " + task.getAssignee());
//                    System.out.println("  " + task.getDescription());
//                    Map<String,Object> variables = new HashMap<String, Object>();
//                    variables.put("chiefApprovePassed", true);
//                    taskService.complete(task.getId(), variables);
//                    System.out.println( "------ ok ");
////                    task.setAssignee("10001");
////                    taskService.setAssignee(  task.getId(),"10001" );
//
//                }


            }
        };
    }
}
