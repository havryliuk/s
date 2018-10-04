package com.havryliuk.store.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/store/*")
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        //to be implemented
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (!request.getRequestURI().contains("store/login") && null == request.getSession().getAttribute("userId")) {
            request.getRequestDispatcher("/").forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        //to be implemented
    }
}
