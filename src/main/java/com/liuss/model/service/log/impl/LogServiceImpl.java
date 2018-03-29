package com.liuss.model.service.log.impl;

import com.liuss.model.entity.log.LoginLog;
import com.liuss.model.mapper.log.LoginLogMapper;
import com.liuss.model.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LoginLogMapper loginLogMapper;
    @Override
    public List<LoginLog> findAllLoginLogs() {
        return loginLogMapper.findAllLoginLog();
    }

    @Override
    public Integer deleteLoginLog(Integer id) {
        return loginLogMapper.deleteLoginLog(id);
    }
}
