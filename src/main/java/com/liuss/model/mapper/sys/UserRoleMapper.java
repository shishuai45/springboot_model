package com.liuss.model.mapper.sys;

import com.liuss.model.entity.sys.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    List<UserRole>findUserRolesByUserId(@Param("userid")Integer userid);
    Integer insertUserRole(UserRole userRole);
    Integer updateUserRole(UserRole userRole);
    Integer deleteUserRole(@Param("id")Integer id);
}
