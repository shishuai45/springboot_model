package com.liuss.model.service.sys.impl;

import com.liuss.model.entity.sys.Module;
import com.liuss.model.mapper.sys.ModuleMapper;
import com.liuss.model.service.sys.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleMapper moduleMapper;
    @Override
    public List<Module> findModules() {
        return moduleMapper.findAllModule();
    }

    @Override
    public List<Module> findModulesByName(String name) {
        return moduleMapper.findModuleByName(name);
    }

    @Override
    public Integer saveModule(Module module) {
        return moduleMapper.insertModule(module);
    }

    @Override
    public Integer updateModule(Module module) {
        return moduleMapper.updateModule(module);
    }

    @Override
    public Integer deleteModule(Integer id) {
        return moduleMapper.deleteModule(id);
    }
}
