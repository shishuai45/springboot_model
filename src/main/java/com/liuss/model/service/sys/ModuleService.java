package com.liuss.model.service.sys;

import com.liuss.model.entity.sys.Module;

import java.util.List;

public interface ModuleService {
    List<Module>findModules();
    Integer findModulesCount();
    List<Module>findModulesByStartAndCount(int start,int count);
    List<Module>findModulesByName(String name);
    Integer saveModule(Module module);
    Integer deleteModule(Integer id);
}
