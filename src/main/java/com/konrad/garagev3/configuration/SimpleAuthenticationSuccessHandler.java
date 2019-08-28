package com.konrad.garagev3.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SimpleAuthenticationSuccessHandler {

//    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication authentication)
//            throws IOException, ServletException {
//
//        Collection <? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        authorities.forEach(authority -> {
//            if(authority.getAuthority().equals("ROLE_USER")) {
//                try {
//                    redirectStrategy.sendRedirect(arg0, arg1, "/contact");
//                } catch (Exception e) {
//                    log.error(e.getMessage(), e);
//                }
//            } else if(authority.getAuthority().equals("ROLE_ADMIN")) {
//                try {
//                    redirectStrategy.sendRedirect(arg0, arg1, "/contact");
//                } catch (Exception e) {
//                    log.error(e.getMessage(), e);
//                }
//            } else {
//                throw new IllegalStateException();
//            }
//        });
//
//    }
}
