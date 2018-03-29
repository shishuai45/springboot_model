package com.liuss.model.service.sys;

import com.liuss.model.entity.sys.UserRole;

import java.util.List;

public interface UserRoleService {
    Integer saveUserRole(UserRole userRole);
    List<UserRole>findUserRolesByUserId(Integer userid);
}
