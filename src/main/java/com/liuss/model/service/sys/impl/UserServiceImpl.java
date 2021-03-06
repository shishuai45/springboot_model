package com.liuss.model.service.sys.impl;

import com.liuss.model.entity.sys.User;
import com.liuss.model.mapper.sys.UserMapper;
import com.liuss.model.service.sys.UserService;
import com.liuss.model.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserById(Integer id) {
        return userMapper.findUser(id);
    }

    @Override
    public User findUserByLoginname(String loginname) {
        return userMapper.findUserByLoginName(loginname);
    }

    @Override
    public String getNameByUsername(String username) {
        User user=userMapper.findUserByLoginName(username);
        if(user!=null)
            return user.getName();
        return null;
    }

    @Override
    public String getMenusByUsername(String username) {
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
    public List<User> findUsersByName(String name) {
        return userMapper.findUsersByName(name);
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
