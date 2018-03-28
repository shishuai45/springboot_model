package com.liuss.model.service.sys.impl;

import com.liuss.model.entity.sys.Menu;
import com.liuss.model.mapper.sys.MenuMapper;
import com.liuss.model.service.sys.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> findMenus() {
        return menuMapper.findAllMenus();
    }

    @Override
    public Integer saveMenu(Menu menu) {
        return menuMapper.insertMenu(menu);
    }

    @Override
    public Integer updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }

    @Override
    public Integer deleteMenu(Integer id) {
        return menuMapper.deleteMenu(id);
    }
}
