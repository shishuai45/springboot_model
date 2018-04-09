package com.liuss.model.service.sys;

import com.liuss.model.entity.sys.Menu;
import com.liuss.model.model.sys.Mod_Menu;

import java.util.List;

public interface MenuService {
    List<Menu> findMenus();
    List<Menu> findMenusByModuleId(int moduleId,int start,int limit);
    int findMenuCountByModuleId(int moduleId);
    List<Mod_Menu>findMenusByUserid(Integer userid);
    Integer saveMenu(Menu menu);
    Integer deleteMenu(Integer id);
}
