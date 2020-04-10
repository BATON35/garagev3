package com.konrad.garagev3.auth;

import com.konrad.garagev3.util.SecurityUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }
        Optional<UsernamePasswordAuthenticationToken> optionalUsernamePasswordAuthenticationToken = SecurityUtil.parsToken(token);
        if (optionalUsernamePasswordAuthenticationToken.isPresent()) {
            SecurityContextHolder.getContext().setAuthentication(optionalUsernamePasswordAuthenticationToken.get());
            chain.doFilter(request, response);
        }else
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }



}
