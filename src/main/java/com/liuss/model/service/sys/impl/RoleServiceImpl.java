package com.liuss.model.service.sys.impl;

import com.liuss.model.entity.sys.Role;
import com.liuss.model.mapper.sys.RoleMapper;
import com.liuss.model.service.sys.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public Integer saveRole(Role role) {
        return roleMapper.insertRole(role);
    }

    @Override
    public Integer deleteRole(Integer id) {
        return roleMapper.deleteRole(id);
    }

    @Override
    public List<Role> findRolesByName(String name) {
        return roleMapper.findRolesByName(name);
    }
}
