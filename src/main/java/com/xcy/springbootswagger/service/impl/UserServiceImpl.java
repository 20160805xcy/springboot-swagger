package com.xcy.springbootswagger.service.impl;

import com.xcy.springbootswagger.mapper.UserMapper;
import com.xcy.springbootswagger.model.User;
import com.xcy.springbootswagger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xcy
 * @date 2018/12/05 14:37
 * @description
 * @since V1.0.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listAll() {
        List<User> userList = userMapper.selectAll();
        return userList;
    }

    @Override
    public User findOneById(Integer id) {
        return userMapper.findOneById(id);
    }

    @Override
    public void addOne(User user) {
        userMapper.insert(user);
    }

    @Override
    public void deleteOneById(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateById(Integer id) {
        userMapper.updateById(id);
    }
}
