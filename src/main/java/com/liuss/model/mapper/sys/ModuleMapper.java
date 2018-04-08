package com.liuss.model.mapper.sys;

import com.liuss.model.entity.sys.Module;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ModuleMapper {
    List<Module>findAllModule();
    Integer findAllModuleCount();
    List<Module>findModulesByStartAndCount(@Param("start")Integer start,@Param("count")Integer count);
    List<Module>findModuleByName(@Param("name")String name);
    List<Module> findModuleByUserId(@Param("userid")Integer userid);
    Module findModuleById(@Param("id")Integer id);
    Integer insertModule(Module module);
    Integer updateModule(Module module);
    Integer deleteModule(@Param("id")Integer id);
}
