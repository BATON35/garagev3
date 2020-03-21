package com.konrad.garagev3.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public Boolean hasAccessToDeletedUserList(Boolean deleted) {
        if (deleted != null && deleted)
            return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                    .stream()
                    .anyMatch(r -> "ROLE_ADMIN".equals(r.getAuthority()));
        return true;
    }
}
