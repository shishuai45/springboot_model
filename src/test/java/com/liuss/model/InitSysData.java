package com.liuss.model;

import com.liuss.model.entity.sys.Menu;
import com.liuss.model.entity.sys.Module;
import com.liuss.model.entity.sys.User;
import com.liuss.model.service.sys.MenuService;
import com.liuss.model.service.sys.ModuleService;
import com.liuss.model.service.sys.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class InitSysData {
    @Autowired
    private UserService userService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private MenuService menuService;
    @Test
    public void addUser()
    {
        List<User> users=userService.findAllUsers();
        for (User user :
                users) {
            userService.deleteUser(user.getId());
        }
        User user=new User();
        user.setName("系统管理员");
        user.setLoginName("admin");
        user.setPassword("123456");
        userService.addUserInfo(user);
    }
    @Test
    public void addModule()
    {
        List<Module> modules=moduleService.findModules();
        for (Module mod :
                modules) {
            moduleService.deleteModule(mod.getId());
        }
        Module module=new Module();
        module.setName("系统管理");
        module.setSeq(10);
        moduleService.saveModule(module);
        module=new Module();
        module.setName("日志查询");
        module.setSeq(9);
        moduleService.saveModule(module);
    }
    @Test
    public void addMenu()
    {
        List<Menu>menus=menuService.findMenus();
        for (Menu men:menus){
            menuService.deleteMenu(men.getId());
        }
        List<Module> modules=moduleService.findModulesByName("系统管理");
        if(modules.size()>0){
            Menu menu=new Menu();
            menu.setModuleId(modules.get(0).getId());
            menu.setName("用户管理");
            menu.setSeq(1);
            menu.setUrl("/authorized/usermanage");
            menuService.saveMenu(menu);
            menu=new Menu();
            menu.setModuleId(modules.get(0).getId());
            menu.setName("模块管理");
            menu.setSeq(2);
            menu.setUrl("/authorized/modulemanage");
            menuService.saveMenu(menu);
            menu=new Menu();
            menu.setModuleId(modules.get(0).getId());
            menu.setName("菜单管理");
            menu.setSeq(3);
            menu.setUrl("/authorized/menumanage");
            menuService.saveMenu(menu);
            menu.setModuleId(modules.get(0).getId());
            menu.setName("角色管理");
            menu.setSeq(4);
            menu.setUrl("/authorized/rolemanage");
            menuService.saveMenu(menu);
        }
        modules=moduleService.findModulesByName("日志");
        if(modules.size()>0){
            Menu menu=new Menu();
            menu.setModuleId(modules.get(0).getId());
            menu.setName("用户登录日志查询");
            menu.setSeq(1);
            menu.setUrl("/authorized/loginlogview");
            menuService.saveMenu(menu);
        }
    }
}
