package com.liuss.model.service.sys;

import com.liuss.model.entity.sys.UserRole;

import java.util.List;

public interface UserRoleService {
    Integer saveUserRole(UserRole userRole);
    Integer deleteUserRole(Integer id);
    List<UserRole>findUserRolesByUserId(Integer userid);
}
