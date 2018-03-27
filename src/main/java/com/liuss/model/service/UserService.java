package com.liuss.model.service;

import com.liuss.model.entity.sys.User;

import java.util.List;

public interface UserService {
    User findUserById(Integer id);
    int addUserInfo(User user);
    List<User>findAllUsers();
    int findUserCount();
    int updateUser(User user);
    int deleteUser(Integer userId);
    List<User>findUserByIndexAndSize(Integer currentIndex,Integer size);
}
