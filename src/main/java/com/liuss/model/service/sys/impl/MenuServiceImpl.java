package com.liuss.model.service.sys.impl;

import com.liuss.model.entity.sys.Menu;
import com.liuss.model.entity.sys.Module;
import com.liuss.model.entity.sys.User;
import com.liuss.model.mapper.sys.MenuMapper;
import com.liuss.model.mapper.sys.ModuleMapper;
import com.liuss.model.model.sys.Mod_Menu;
import com.liuss.model.service.sys.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private ModuleMapper moduleMapper;
    @Override
    public List<Menu> findMenus() {
        return menuMapper.findAllMenus();
    }

    @Override
    public List<Mod_Menu> findMenusByUserid(Integer userid) {
        List<Mod_Menu> result=new ArrayList<>();
        List<Module> modules=moduleMapper.findModuleByUserId(userid);
        List<Menu> menus=menuMapper.findMenusByUserId(userid);
        boolean expanded=true;
        for (Module module:modules){
            Mod_Menu mod_menu=new Mod_Menu();
            mod_menu.setId(module.getId());
            mod_menu.setText(module.getName());
            mod_menu.setIconCls(module.getIcon());
            mod_menu.setExpanded(expanded);
            List<Menu>menuList=menus.stream().filter(menu ->menu.getModuleId().equals(module.getId())).collect(Collectors.toList());
            mod_menu.setChildren(getChildrenMenus(menuList,null));
            result.add(mod_menu);
            expanded=false;
        }
        return result;
    }

    private List<Mod_Menu> getChildrenMenus(List<Menu> menus,Integer parentid){
        List<Mod_Menu> result=new ArrayList<>();
        List<Menu>menuList=menus.stream().filter(menu -> menu.getParentId()==parentid).collect(Collectors.toList());
        for (Menu menu :
                menuList) {
            Mod_Menu mod_menu=new Mod_Menu();
            mod_menu.setId(menu.getId());
            mod_menu.setText(menu.getName());
            mod_menu.setIconCls(menu.getIcon());
            mod_menu.setUrl(menu.getUrl());
            mod_menu.setChildren(getChildrenMenus(menus,menu.getId()));
            mod_menu.setLeaf(mod_menu.getChildren().size()==0);
            result.add(mod_menu);
        }
        return result;
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
