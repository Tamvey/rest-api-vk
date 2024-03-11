package com.test.web.util;

import com.test.web.enitiy.Audit;
import com.test.web.repository.AuditRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;


@Component
public class RequestHandler implements HandlerInterceptor  {

    @Autowired
    AuditRepository auditRepository;
    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

        Audit attempt = new Audit(SecurityContextHolder.getContext().getAuthentication().getName(),
                                  String.valueOf(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()),
                                    new Date().toString(), request.getMethod(), request.getRequestURI(),
                                    String.valueOf(response.getStatus()));
        auditRepository.save(attempt);
    }
}
