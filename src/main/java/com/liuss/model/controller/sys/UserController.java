package com.liuss.model.controller.sys;

import com.liuss.model.controller.BaseController;
import com.liuss.model.entity.sys.User;
import com.liuss.model.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @RequestMapping("/usermanage")
    public String userManage(){
        return "sys/usermanage";
    }
    @ResponseBody
    @RequestMapping("/findUserById")
    public User findUserById(@RequestParam("userId") Integer userId)
    {
        return userService.findUserById(userId);
    }
    @ResponseBody
    @RequestMapping("/addUserInfo")
    public int addUserInfo(){
        User user=new User();
        user.setName("liuss");
        user.setLoginName("liuss");
        user.setPassword("123456");
        return userService.addUserInfo(user);
    }
    @ResponseBody
    @RequestMapping("/updateUserInfo")
    public int updateUserInfo(@RequestParam("id") int id)
    {
        User user=userService.findUserById(id);
        user.setPassword("000000");
        return userService.updateUser(user);
    }
    @ResponseBody
    @RequestMapping("/deleteUserInfo")
    public int deleteUserInfo(@RequestParam("userId")Integer userId)
    {
        return userService.deleteUser(userId);
    }
    @ResponseBody
    @RequestMapping("/getAllUsers")
    public List<User>getAllUsers()
    {
        return userService.findAllUsers();
    }
    @ResponseBody
    @RequestMapping("/findUserByPageAndSize")
    public List<User> findUserByPageAndSize(@RequestParam("page") Integer page,@RequestParam("size")Integer size){
        return userService.findUserByIndexAndSize(page*size,size);
    }
    @RequestMapping("/greeting")
    public String greeting() {
        return "welcome";
    }
}
