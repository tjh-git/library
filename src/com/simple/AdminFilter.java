package com.simple;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by lovesyxfuffy on 2016/7/4.
 */
@WebFilter(urlPatterns ="/*",filterName = "admin_filter")
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        HttpSession session=request.getSession();
        if(request.getRequestURI().equals("/login")||request.getRequestURI().matches(".*?resources.*?")){

            filterChain.doFilter(request,response);
        }
        else{
            if(session.getAttribute("school_id")==null) {
                response.sendRedirect("/login");
            }
            else {
                filterChain.doFilter(request,response);
            }
        }


    }

    @Override
    public void destroy() {

    }
}
