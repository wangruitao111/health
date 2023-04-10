package com.stx.health.filter;


import com.alibaba.fastjson.JSON;
import com.stx.health.common.BaseContext;
import com.stx.health.common.Contants;
import com.stx.health.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
@Component
@Slf4j
public class LoginCheckFilter implements Filter {

    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        log.info("{}", BaseContext.getCurrentId());

        String requestURI = request.getRequestURI();

        String[] urls = new String[]{
                "/user/login",
                "/user/logout",
                "/user/saveUser",
                "/admin/login",
                "/admin/logout"
        };

        boolean check = check(urls, requestURI);

        if (check){
            filterChain.doFilter(request, response);
            return;
        }

        if (request.getSession().getAttribute(Contants.SESSION_USER) != null){
            Long userId = (Long) request.getSession().getAttribute(Contants.SESSION_USER);
            BaseContext.setCurrentId(userId);
            filterChain.doFilter(request, response);
            return;
        }

        if (request.getSession().getAttribute(Contants.SESSION_ADMIN) != null){
            Long adminId = (Long) request.getSession().getAttribute(Contants.SESSION_ADMIN);
            BaseContext.setCurrentId(adminId);
            filterChain.doFilter(request, response);
            return;
        }

        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;

    }

    public boolean check(String[] urls, String requestURI){
        for(String url : urls){
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) return true;
        }
        return false;
    }
}
