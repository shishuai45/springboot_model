package com.liuss.model.service.log;

import com.liuss.model.entity.log.LoginLog;
import com.liuss.model.model.log.Mod_LoginLog;

import java.util.Date;
import java.util.List;

public interface LogService {
    List<LoginLog>findAllLoginLogs();
    Integer deleteLoginLog(Integer id);
    Integer getLoginLogCount(String username,Date loginstarttime,Date loginendtime);
    List<Mod_LoginLog>getLoginLogByIndexAndSize(int index, int size,String username, Date loginstarttime,Date loginendtime);
}
