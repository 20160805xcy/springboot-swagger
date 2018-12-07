package com.xcy.springbootswagger.mapper;

import com.xcy.springbootswagger.model.User;
import com.xcy.springbootswagger.util.MyMapper;
import io.swagger.models.auth.In;

public interface UserMapper extends MyMapper<User> {

    User findOneById(Integer id);

    void updateById(Integer id);
}