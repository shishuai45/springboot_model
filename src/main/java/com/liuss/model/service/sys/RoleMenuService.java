package com.liuss.model.service.sys;

import com.liuss.model.entity.sys.RoleMenu;

import java.util.List;

public interface RoleMenuService {
    Integer saveRoleMenu(RoleMenu roleMenu);
    Integer deleteRoleMenu(Integer id);
    List<RoleMenu>findRoleMenusByRoleId(Integer roleid);
}
