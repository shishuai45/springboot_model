package com.liuss.model.service.sys;

import com.liuss.model.entity.sys.Menu;
import com.liuss.model.model.sys.Mod_Menu;

import java.util.List;

public interface MenuService {
    List<Menu> findMenus();
    List<Mod_Menu>findMenusByUserid(Integer userid);
    Integer saveMenu(Menu menu);
    Integer updateMenu(Menu menu);
    Integer deleteMenu(Integer id);
}
