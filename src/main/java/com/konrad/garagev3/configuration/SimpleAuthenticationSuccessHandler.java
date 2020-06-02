package com.konrad.garagev3.configuration;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication authentication)
            throws IOException, ServletException {

        Collection <? extends GrantedAuthority> authorities = authentication.getAuthorities();
        authorities.forEach(authority -> {
            if(authority.getAuthority().equals("ROLE_USER")) {
                try {
                    redirectStrategy.sendRedirect(arg0, arg1, "/index");
                } catch (Exception e) {
                    log.error(e.getMessage() , e);
                }
            } else if(authority.getAuthority().equals("ROLE_ADMIN")) {
                try {
                    redirectStrategy.sendRedirect(arg0, arg1, "/admin");
                } catch (Exception e) {
                    log.error(e.getMessage() , e);
                }
            } else {
                throw new IllegalStateException();
            }
        });

    }
}
