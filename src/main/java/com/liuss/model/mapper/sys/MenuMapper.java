package com.liuss.model.mapper.sys;

import com.liuss.model.entity.sys.Menu;
import com.liuss.model.entity.sys.Module;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MenuMapper {
    List<Menu> findMenusByUserId(@Param("userid")Integer userid);
    List<Menu>findMenusByModuleid(@Param("moduleid")Integer moduleid,@Param("start")Integer start,@Param("count")Integer count);
    int findMenuCountByModuleid(@Param("moduleid")Integer moduleid);
    List<Menu>findAllMenus();
    Menu findMenuById(@Param("id")Integer id);
    Integer insertMenu(Menu menu);
    Integer updateMenu(Menu menu);
    Integer deleteMenu(@Param("id")Integer id);
}
