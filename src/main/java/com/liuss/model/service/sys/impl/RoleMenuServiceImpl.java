package com.liuss.model.service.sys.impl;

import com.liuss.model.entity.sys.RoleMenu;
import com.liuss.model.mapper.sys.RoleMenuMapper;
import com.liuss.model.service.sys.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Override
    public Integer saveRoleMenu(RoleMenu roleMenu) {
        return roleMenuMapper.insertRoleMenu(roleMenu);
    }

    @Override
    public Integer deleteRoleMenu(Integer id) {
        return roleMenuMapper.deleteRoleMenu(id);
    }

    @Override
    public List<RoleMenu> findRoleMenusByRoleId(Integer roleid) {
        return roleMenuMapper.findRoleMenuByRoleId(roleid);
    }
}
