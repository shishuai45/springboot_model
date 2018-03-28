package com.liuss.model.service.sys;

import com.liuss.model.entity.sys.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> findMenus();
    Integer saveMenu(Menu menu);
    Integer updateMenu(Menu menu);
    Integer deleteMenu(Integer id);
}
