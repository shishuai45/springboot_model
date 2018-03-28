package com.liuss.model.mapper.sys;

import com.liuss.model.entity.sys.Module;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ModuleMapper {
    List<Module>findAllModule();
    List<Module>findModuleByName(@Param("name")String name);
    Integer insertModule(Module module);
    Integer updateModule(Module module);
    Integer deleteModule(@Param("id")Integer id);
}
