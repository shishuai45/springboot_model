package com.liuss.model.service.impl;

import com.liuss.model.entity.sys.User;
import com.liuss.model.mapper.sys.UserMapper;
import com.liuss.model.service.UserService;
import com.liuss.model.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserById(Integer id) {
        return userMapper.findUser(id);
    }

    @Override
    public String getNameByUsername(String username) {
        User user=userMapper.findUserByLoginName(username);
        if(user!=null)
            return user.getName();
        return null;
    }

    @Override
    @Transactional
    public int addUserInfo(User user) {
        user.setPassword(EncryptUtil.Md5(user.getPassword()));
        return userMapper.insertUser(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userMapper.findAllUser();
    }

    @Override
    public int findUserCount() {
        return userMapper.findUserCount();
    }

    @Override
    public int updateUser(User user) {
        User olduser=userMapper.findUser(user.getId());
        if(!olduser.getPassword().equals(user.getPassword()))
        {
            user.setPassword(EncryptUtil.Md5(user.getPassword()));
        }
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUser(Integer userId) {
        return userMapper.deleteUser(userId);
    }

    @Override
    public List<User> findUserByIndexAndSize(Integer currentIndex, Integer size) {
        return userMapper.findUserByPageAndCount(currentIndex,size);
    }
}
