package com.joywayi.configuration;

import org.flowable.spring.ProcessEngineFactoryBean;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = FlowableConfiguration.class)
public class FlowableConfiguration {
    @Primary
    @Bean(name = "processEngineConfiguration")
    public SpringProcessEngineConfiguration
    getSpringProcessEngineConfiguration(@Autowired DataSource dataSource,
                                        @Autowired PlatformTransactionManager transactionManager) {
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setTransactionManager(transactionManager);
        configuration.setDatabaseSchemaUpdate("true");
        configuration.setAsyncExecutorActivate(true);
        return configuration;
    }
    @Bean
    public ProcessEngineFactoryBean getProcessEngineFactoryBean(@Qualifier("processEngineConfiguration") SpringProcessEngineConfiguration processEngineConfiguration){
        ProcessEngineFactoryBean bean = new ProcessEngineFactoryBean();
        bean.setProcessEngineConfiguration( processEngineConfiguration );
        return  bean;

    }


}