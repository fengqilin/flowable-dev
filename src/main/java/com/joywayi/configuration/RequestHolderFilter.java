package com.joywayi.configuration;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/api/job/*",filterName = "传输参数")
public class RequestHolderFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter ---------------");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
