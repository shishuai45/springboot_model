package com.liuss.model;

import com.liuss.model.model.sys.Mod_Menu;
import com.liuss.model.service.sys.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceTest {
    @Autowired
    private MenuService menuService;
    @Test
    public void test1()
    {
        List<Mod_Menu> mod_menus=menuService.findMenusByUserid(15);
        System.out.println(mod_menus.get(0).getChildren().size());
    }
}
