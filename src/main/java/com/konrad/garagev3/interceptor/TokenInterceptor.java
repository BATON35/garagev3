package com.konrad.garagev3.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("authorization");
        if (token == null || !token.startsWith("Bearer")) {
            return true;
        }
        Claims claims = Jwts.parser().setSigningKey("test33").parseClaimsJws(token.replace("Bearer", "")).getBody();
        Date date = new Date(System.currentTimeMillis() + 60_000 * 60 * 24);
        claims.setExpiration(date);
        String updatedToken = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "test33")
                .compact();

        response.setHeader("Authorization", updatedToken);
        response.addHeader("access-control-expose-headers", "Authorization");
      //  response.setHeader("expires", date.toString());
        return true;
    }


}

