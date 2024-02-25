package com.csmtech.auth.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

@WebFilter("*")
public class CorsFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    	 HttpServletResponse httpServletResponse = (HttpServletResponse) response;

    	    httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");

    	    httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");

    	    httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");

    	    chain.doFilter(request, response);

    }

}
