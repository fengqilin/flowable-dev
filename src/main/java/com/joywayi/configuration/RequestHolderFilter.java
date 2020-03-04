package com.joywayi.configuration;

import com.joywayi.common.http.RequestHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Date;

@WebFilter(urlPatterns = "/api/job/*",filterName = "传输参数")
public class RequestHolderFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter ---------------");
        RequestHolder.setValue("name","feng");
        RequestHolder.setValue("days",new Date().getTime());
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
