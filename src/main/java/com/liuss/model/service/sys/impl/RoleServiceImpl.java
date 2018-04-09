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
        Role oldRole=roleMapper.findRoleById(role.getId());
        if(oldRole==null)
        {
            return roleMapper.insertRole(role);
        }
        else {
            return roleMapper.updateRole(role);
        }
    }

    @Override
    public Integer deleteRole(Integer id) {
        return roleMapper.deleteRole(id);
    }

    @Override
    public List<Role> findRolesByName(String name) {
        return roleMapper.findRolesByName(name);
    }

    @Override
    public Integer findRoleCount() {
        return roleMapper.findRoleCount();
    }

    @Override
    public List<Role> findRolesByStartAndCount(int start, int count) {
        return roleMapper.findRolesByStartAndCount(start,count);
    }
}
