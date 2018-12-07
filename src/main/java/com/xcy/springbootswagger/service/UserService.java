package com.xcy.springbootswagger.service;

import com.xcy.springbootswagger.model.User;

import java.util.List;

/**
 * @author xcy
 * @date 2018/12/05 14:36
 * @description
 * @since V1.0.0
 */
public interface UserService {

    List<User> listAll();

    User findOneById(Integer id);

    void addOne(User user);

    void deleteOneById(Integer id);

    void updateById(Integer id);
}
