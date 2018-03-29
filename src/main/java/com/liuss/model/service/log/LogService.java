package com.liuss.model.service.log;

import com.liuss.model.entity.log.LoginLog;
import com.liuss.model.model.log.Mod_LoginLog;

import java.util.List;

public interface LogService {
    List<LoginLog>findAllLoginLogs();
    Integer deleteLoginLog(Integer id);
    Integer getLoginLogCount();
    List<Mod_LoginLog>getLoginLogByIndexAndSize(int index, int size);
}
