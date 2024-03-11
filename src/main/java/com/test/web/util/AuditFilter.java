package com.test.web.util;

import com.test.web.enitiy.Audit;
import com.test.web.repository.AuditRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Date;


public class AuditFilter extends GenericFilterBean {

    AuditRepository auditRepository;

    public AuditFilter(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name;
        String alreadyAuth = "false";
        if (auth == null) {
            name = "unknown";
        } else {
            name = auth.getName();
            alreadyAuth = "true";
        }
        Audit attempt = new Audit(name, alreadyAuth,
                new Date().toString(),
                ((HttpServletRequest) servletRequest).getMethod(),
                ((HttpServletRequest) servletRequest).getRequestURI(),
                "attempt");
        auditRepository.save(attempt);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
