package com.liuss.model.service.sys.impl;

import com.liuss.model.entity.sys.UserRole;
import com.liuss.model.mapper.sys.UserRoleMapper;
import com.liuss.model.service.sys.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public Integer saveUserRole(UserRole userRole) {
        return userRoleMapper.insertUserRole(userRole);
    }

    @Override
    public List<UserRole> findUserRolesByUserId(Integer userid) {
        return userRoleMapper.findUserRolesByUserId(userid);
    }
}
