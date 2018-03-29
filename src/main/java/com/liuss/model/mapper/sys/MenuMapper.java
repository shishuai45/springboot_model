package com.liuss.model.mapper.sys;

import com.liuss.model.entity.sys.Menu;
import com.liuss.model.entity.sys.Module;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MenuMapper {
    List<Menu> findMenusByUserId(@Param("userid")Integer userid);
    List<Menu>findMenusByModuleid(@Param("moduleid")Integer moduleid);
    List<Menu>findAllMenus();
    Integer insertMenu(Menu menu);
    Integer updateMenu(Menu menu);
    Integer deleteMenu(@Param("id")Integer id);
}
