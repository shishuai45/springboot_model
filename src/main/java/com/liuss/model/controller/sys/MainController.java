package com.liuss.model.controller.sys;

import com.liuss.model.controller.BaseController;
import com.liuss.model.entity.sys.User;
import com.liuss.model.model.sys.Mod_Menu;
import com.liuss.model.service.sys.MenuService;
import com.liuss.model.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController extends BaseController{
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;
    @RequestMapping("/main")
    public String main(){
        return "main";
    }
    @RequestMapping("/footer")
    public String footer()
    {
        return "/layout/footer";
    }
    @RequestMapping("/header")
    public String header()
    {
        return "/layout/header";
    }
    @RequestMapping("/home")
    public String home()
    {
        return "/layout/home";
    }
    @RequestMapping("/getusername")
    @ResponseBody
    public Map<String, Object> getUserName(HttpServletRequest request){
        String loginName=getLoginName(request.getSession());
        Map<String,Object>result=new HashMap<>();
        result.put("success",true);
        result.put("username",userService.getNameByUsername(loginName));
        return result;
    }
    @RequestMapping("/getmenus")
    @ResponseBody
    public List<Mod_Menu> getmenus(HttpServletRequest request){
        String loginName=getLoginName(request.getSession());
        User user=userService.findUserByLoginname(loginName);
        if(user!=null){
            return menuService.findMenusByUserid(user.getId());
        }
        return null;
    }
}
