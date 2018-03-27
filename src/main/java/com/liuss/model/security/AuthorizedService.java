package com.liuss.model.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;


public interface AuthorizedService extends UserDetailsService {
    LogoutHandler getLogoutHandler();
    AuthenticationSuccessHandler getAuthenticationSuccessHandler(String defaultSuccessUrl);
}
