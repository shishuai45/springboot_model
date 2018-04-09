package com.liuss.model.mapper.sys;

import com.liuss.model.entity.sys.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    Integer findRoleCount();
    List<Role>findRolesByStartAndCount(@Param("start")Integer start,@Param("count")Integer count);
    Role findRoleById(@Param("id")Integer id);
    List<Role>findRolesByName(@Param("name")String name);
    List<Role>findRolesByUserid(@Param("userid")Integer userid);
    Integer insertRole(Role role);
    Integer updateRole(Role role);
    Integer deleteRole(@Param("id")Integer id);
}
