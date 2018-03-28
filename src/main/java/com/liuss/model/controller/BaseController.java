package com.liuss.model.controller;

import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequestMapping("/authorized")
public abstract class BaseController {
    protected String getLoginName(HttpSession session)
    {
        try{
            User user=(User) ((SecurityContextImpl)session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY)).getAuthentication().getPrincipal();
            if(user!=null)
                return user.getUsername();
        }catch (Exception ignored){

        }
        return "";
    }
}
