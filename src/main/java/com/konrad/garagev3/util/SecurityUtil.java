package com.konrad.garagev3.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SecurityUtil {
    public static Optional<UsernamePasswordAuthenticationToken> parsToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey("test33")
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
        List<GrantedAuthority> grantedAuthorities = new LinkedList<>();
        String authorities = claims.get("authorities", String.class);
        if (authorities != null && !authorities.isEmpty()) {
            grantedAuthorities = Arrays.stream(authorities.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        String userLogin = claims.getSubject();
        if (userLogin != null) {
            return Optional.of(new UsernamePasswordAuthenticationToken(userLogin, null, grantedAuthorities));
        } else {
            return Optional.empty();
        }
    }
}
