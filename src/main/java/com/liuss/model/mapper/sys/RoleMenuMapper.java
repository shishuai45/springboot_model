package com.liuss.model.mapper.sys;

import com.liuss.model.entity.sys.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMenuMapper {
    List<RoleMenu> findRoleMenuByRoleId(@Param("roleid")Integer roleid);
    Integer insertRoleMenu(RoleMenu roleMenu);
    Integer updateRoleMenu(RoleMenu roleMenu);
    Integer deleteRoleMenu(@Param("id")Integer id);
}
