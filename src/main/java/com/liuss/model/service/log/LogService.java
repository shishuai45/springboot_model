package com.liuss.model.service.log;

import com.liuss.model.entity.log.LoginLog;

import java.util.List;

public interface LogService {
    List<LoginLog>findAllLoginLogs();
    Integer deleteLoginLog(Integer id);
}
