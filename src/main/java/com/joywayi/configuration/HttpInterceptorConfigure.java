package com.joywayi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HttpInterceptorConfigure implements WebMvcConfigurer {
    @Autowired
    CleanParamsHttpInterceptor cleanParamsHttpInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cleanParamsHttpInterceptor).addPathPatterns("/api/job/**");

    }
}