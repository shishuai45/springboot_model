package com.liuss.model.security.impl;

import com.liuss.model.entity.log.LoginLog;
import com.liuss.model.entity.sys.User;
import com.liuss.model.mapper.log.LoginLogMapper;
import com.liuss.model.mapper.sys.UserMapper;
import com.liuss.model.security.AuthorizedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class AuthorizedServiceImpl implements AuthorizedService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginLogMapper loginLogMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userMapper.findUserByLoginName(s);
        if(user==null){
            throw new UsernameNotFoundException(s+" do not exist!");
        }
        else {
            List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            org.springframework.security.core.userdetails.User uu=new org.springframework.security.core.userdetails.User(s,user.getPassword(),grantedAuthorities);
            return uu;
        }
    }
    public LogoutHandler getLogoutHandler(){
        return (httpServletRequest, httpServletResponse, authentication) -> {
            org.springframework.security.core.userdetails.User user=(org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            User uu=userMapper.findUserByLoginName(user.getUsername());
            if(uu!=null)
                loginLogMapper.logoutLog(uu.getId());
        };
    }

    @Override
    public AuthenticationSuccessHandler getAuthenticationSuccessHandler(String defaultSuccessUrl) {
        return new LoginSuccesssHandler(defaultSuccessUrl);
    }
    private class LoginSuccesssHandler extends SavedRequestAwareAuthenticationSuccessHandler {
        public LoginSuccesssHandler()
        {

        }
        public LoginSuccesssHandler(String defaultSuccessUrl){
            this.setDefaultTargetUrl(defaultSuccessUrl);
            this.setAlwaysUseDefaultTargetUrl(false);
        }
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
            org.springframework.security.core.userdetails.User user=(org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            User uu=userMapper.findUserByLoginName(user.getUsername());
            if(uu!=null){
                loginLogMapper.logoutLog(uu.getId());
                LoginLog loginLog=new LoginLog();
                loginLog.setUserId(uu.getId());
                loginLog.setIp(getIpAddress(request));
                loginLog.setSessionId(request.getRequestedSessionId());
                loginLogMapper.loginLog(loginLog);
            }
            super.onAuthenticationSuccess(request, response, authentication);
        }
        private String getIpAddress(HttpServletRequest request){
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            return ip;
        }
    }
}