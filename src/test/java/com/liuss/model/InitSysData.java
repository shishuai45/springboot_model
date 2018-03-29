package com.liuss.model;

import com.liuss.model.entity.log.LoginLog;
import com.liuss.model.entity.sys.*;
import com.liuss.model.service.log.LogService;
import com.liuss.model.service.sys.*;
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
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private LogService logService;
    @Test
    public void initData()
    {
        deleteData();
        addModule();
        addMenu();
        addRole();
        addUser();
    }
    @Test
    public void deleteData()
    {
        List<LoginLog>loginLogs=logService.findAllLoginLogs();
        for (LoginLog log :
                loginLogs) {
            logService.deleteLoginLog(log.getId());
        }
        List<User> users=userService.findAllUsers();
        for (User user :
                users) {
            List<UserRole> userRoles=userRoleService.findUserRolesByUserId(user.getId());
            for (UserRole userrole :
                    userRoles) {
                userRoleService.deleteUserRole(userrole.getId());
            }
            userService.deleteUser(user.getId());
        }
        List<Role> roles=roleService.findRolesByName("");
        for (Role r :
                roles) {
            List<RoleMenu> roleMenus=roleMenuService.findRoleMenusByRoleId(r.getId());
            for (RoleMenu rm :
                    roleMenus) {
                roleMenuService.deleteRoleMenu(rm.getId());
            }
            roleService.deleteRole(r.getId());
        }
        List<Menu>menus=menuService.findMenus();
        for (Menu men:menus){
            menuService.deleteMenu(men.getId());
        }
        List<Module> modules=moduleService.findModules();
        for (Module mod :
                modules) {
            moduleService.deleteModule(mod.getId());
        }
    }
    @Test
    public void addUser()
    {
        User user=new User();
        user.setName("系统管理员");
        user.setLoginName("admin");
        user.setPassword("123456");
        userService.addUserInfo(user);
        List<User> users1=userService.findUsersByName("系统管理员");
        List<Role>roles=roleService.findRolesByName("超级管理员");
        if(users1.size()>0&&roles.size()>0){
            UserRole userRole=new UserRole();
            userRole.setUserId(users1.get(0).getId());
            userRole.setRoleId(roles.get(0).getId());
            userRoleService.saveUserRole(userRole);
        }
    }
    @Test
    public void addModule()
    {
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
    @Test
    public void addRole()
    {
        Role role=new Role();
        role.setName("超级管理员");
        role.setNameEn("Admin");
        roleService.saveRole(role);
        List<Role> roles1=roleService.findRolesByName("超级管理员");
        if(roles1.size()>0){
            List<Menu>menus=menuService.findMenus();
            for (Menu menu :
                    menus) {
                RoleMenu rolemenu = new RoleMenu();
                rolemenu.setMenuId(menu.getId());
                rolemenu.setRoleId(roles1.get(0).getId());
                roleMenuService.saveRoleMenu(rolemenu);
            }
        }
    }
}
