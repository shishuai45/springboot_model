package com.liuss.model.service.sys;

import com.liuss.model.entity.sys.Role;

import java.util.List;

public interface RoleService {
    Integer saveRole(Role role);
    Integer deleteRole(Integer id);
    List<Role>findRolesByName(String name);
}
