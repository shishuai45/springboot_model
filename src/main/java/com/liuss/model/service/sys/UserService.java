package com.liuss.model.service.sys;

import com.liuss.model.entity.sys.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    User findUserById(Integer id);
    String getNameByUsername(String username);
    String getMenusByUsername(String username);
    int addUserInfo(User user);
    List<User>findAllUsers();
    List<User>findUsersByName(String name);
    int findUserCount();
    int updateUser(User user);
    int deleteUser(Integer userId);
    List<User>findUserByIndexAndSize(Integer currentIndex,Integer size);
}
