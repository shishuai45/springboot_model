package com.liuss.model.controller.sys;

import com.liuss.model.model.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController{
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
}
