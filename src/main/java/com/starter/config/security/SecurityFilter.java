package com.starter.config.security;

import com.starter.model.security.Session;
import com.starter.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.DelegatingFilterProxy;
import scala.Option;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by adam.wells on 18/06/2016.
 */
@Service
public class SecurityFilter extends DelegatingFilterProxy {

    private Logger log = LoggerFactory.getLogger(SecurityFilter.class);

    @Autowired
    SecurityService securityService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {

            HttpServletRequest servletRequest = (HttpServletRequest) request;
            String sessionToken = servletRequest.getHeader("X-Auth-Token");
            if (sessionToken != null){
                Option<Session> session = securityService.findValidSession(sessionToken);
                if (session.isDefined()){
                    securityService.setupSecurityContext(session.get());
                }
            } else {
                if (((HttpServletRequest) request).getMethod() != "GET" && !servletRequest.getRequestURI().contains("/pub/")) {
                    log.info("No X-Auth-Token header... for method=" + servletRequest.getMethod() + " and URI=" + servletRequest.getRequestURI());
                }
            }

            chain.doFilter (request, response);

        } catch( Throwable a ) {
            a.printStackTrace();

            log.warn(a.getMessage());

        } finally {
            // stateless REST
            SecurityContextHolder.clearContext();

        }
    }


}
