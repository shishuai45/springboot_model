package com.liuss.model.mapper.log;

import com.liuss.model.entity.log.LoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
@Mapper
public interface LoginLogMapper {
    List<LoginLog> findAllLoginLog();
    Integer loginLog(LoginLog log);
    Integer logoutLog(@Param("userId")Integer userid);
    Integer deleteLoginLog(@Param("id") Integer id);
    Integer findLoginLogCount( @Param("userName")String username,@Param("loginStartTime")Date loginStartTime, @Param("loginEndTime")Date loginEndTime);
    List<LoginLog>findLoginLogByPageAndCount(@Param("currIndex")Integer index, @Param("pageSize")Integer size,
                                             @Param("userName")String username,
                                             @Param("loginStartTime")Date loginStartTime, @Param("loginEndTime")Date loginEndTime);
}
