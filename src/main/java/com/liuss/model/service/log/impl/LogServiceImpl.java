package com.liuss.model.service.log.impl;

import com.liuss.model.entity.log.LoginLog;
import com.liuss.model.entity.sys.User;
import com.liuss.model.mapper.log.LoginLogMapper;
import com.liuss.model.mapper.sys.UserMapper;
import com.liuss.model.model.log.Mod_LoginLog;
import com.liuss.model.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LoginLogMapper loginLogMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<LoginLog> findAllLoginLogs() {
        return loginLogMapper.findAllLoginLog();
    }

    @Override
    public Integer deleteLoginLog(Integer id) {
        return loginLogMapper.deleteLoginLog(id);
    }

    @Override
    public Integer getLoginLogCount() {
        return loginLogMapper.findLoginLogCount();
    }

    @Override
    public List<Mod_LoginLog> getLoginLogByIndexAndSize(int index, int size) {
        List<LoginLog> loginLogs= loginLogMapper.findLoginLogByPageAndCount(index,size);
        List<Mod_LoginLog> result=new ArrayList<>();
        for (LoginLog log :
                loginLogs) {
            Mod_LoginLog mod_loginLog=new Mod_LoginLog();
            mod_loginLog.setCreatetime(log.getCreateTime());
            mod_loginLog.setId(log.getId());
            mod_loginLog.setLogouttime(log.getLogoutTime());
            mod_loginLog.setSessionid(log.getSessionId());
            mod_loginLog.setIp(log.getIp());
            User user=userMapper.findUser(log.getUserId());
            mod_loginLog.setUsername(user!=null?user.getName():"");
            result.add(mod_loginLog);
        }
        return result;
    }
}
