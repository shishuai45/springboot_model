package com.liuss.model.mapper.sys;

import com.liuss.model.entity.sys.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    List<User>findAllUser();
    User findUser(@Param("id") Integer id);
    User findUserByLoginName(@Param("loginname") String  loginName);
    Integer insertUser(User user);
    Integer updateUser(User user);
    Integer deleteUser(@Param("id") Integer id);
    Integer findUserCount();
    List<User>findUserByPageAndCount(@Param("currIndex")Integer index,@Param("pageSize")Integer size);
}
