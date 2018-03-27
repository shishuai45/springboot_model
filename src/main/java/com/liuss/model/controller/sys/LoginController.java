package com.liuss.model.controller.sys;

import com.liuss.model.model.Msg;
import com.liuss.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController{
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public String login(Model model)
    {
        Msg msg=new Msg();
        msg.setContent("hello boy");
        model.addAttribute("msg",msg);
        return "login";
    }
    @RequestMapping("/authorized/main")
    public String main(){
        return "main";
    }
    @RequestMapping("/authorized/getusername")
    @ResponseBody
    public Map<String, Object> getUserName(HttpServletRequest request){
        User user=(User) ((SecurityContextImpl)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")).getAuthentication().getPrincipal();
        Map<String,Object>result=new HashMap<>();
        result.put("success",true);
        result.put("username",userService.getNameByUsername(user.getUsername()));
        return result;
    }
}
