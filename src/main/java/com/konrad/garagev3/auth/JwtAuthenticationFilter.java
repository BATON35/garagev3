package com.konrad.garagev3.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);
        setUsernameParameter("userLogin");
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Map<String, Object> claims = new HashMap<>();
        StringJoiner stringJoiner = new StringJoiner(",");
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            stringJoiner.add(authority.getAuthority());
        }
        claims.put("authorities", stringJoiner.toString());
        Date date = new Date(System.currentTimeMillis() + 60_000 * 60 * 24);
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(((UserDetails) authResult.getPrincipal()).getUsername())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, "test33")
                .compact();
        response.addHeader("Authorisation", "Bearer " + token);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        PrintWriter printWriter = response.getWriter();
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("token", token);
        responseBody.put("expirationDate", date.toString());
        new ObjectMapper().writeValue(printWriter, responseBody);
    }
}
