package com.liuss.model;

import com.liuss.model.entity.sys.Menu;
import com.liuss.model.entity.sys.Module;
import com.liuss.model.entity.sys.Role;
import com.liuss.model.mapper.sys.MenuMapper;
import com.liuss.model.mapper.sys.ModuleMapper;
import com.liuss.model.mapper.sys.RoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MapperTest {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ModuleMapper moduleMapper;
    @Test
    public void test1()
    {
        List<Menu>menus=menuMapper.findMenusByUserId(14);
        System.out.println(menus.size());
    }
    @Test
    public void test2()
    {
        List<Role>roles=roleMapper.findRolesByUserid(15);
        System.out.println(roles.size());
    }
    @Test
    public void test3()
    {
        List<Module>modules=moduleMapper.findModuleByUserId(15);
        System.out.println(modules.size());
    }
}
