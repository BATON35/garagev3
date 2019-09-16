package com.konrad.garagev3.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.mail.Header;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }
        Claims claims = Jwts.parser()
                .setSigningKey("test33")
                .parseClaimsJws(header.replace("Bearer ", ""))
                .getBody();
        List<GrantedAuthority> grantedAuthorities = new LinkedList<>();
        String authorities = claims.get("authorities", String.class);
        if (authorities != null && !authorities.isEmpty()) {
            grantedAuthorities = Arrays.stream(authorities.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        String userName = claims.getSubject();
        if (userName != null) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, null, grantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            chain.doFilter(request, response);
        }else
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }



}
