package com.xcy.springbootswagger.controller;

import com.xcy.springbootswagger.model.User;
import com.xcy.springbootswagger.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xcy
 * @date 2018/12/05 14:22
 * @description UserController
 * @since V1.0.0
 */
@Api(description = "用户有关")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("查询所有用户")
    @GetMapping("listAll")
    public List<User> listAll() {
        List<User> userList = userService.listAll();
        return userList;
    }

    @ApiOperation("根据ID查询一个用户")
    @GetMapping("findOne/{id}")
    public User findOneById(@PathVariable Integer id){
        User user = userService.findOneById(id);
        return user;
    }

    @ApiOperation("增加一个用户")
    @PostMapping("insertOne")
    public void insetOne(@ModelAttribute User user){
        userService.addOne(user);
    }

    @ApiOperation("删除一个用户")
    @DeleteMapping("deleteOneById/{id}")
    public void deleteOneById(@PathVariable Integer id){
        userService.deleteOneById(id);
    }

    @ApiOperation("修改一个用户")
    @PutMapping("updateById/{id}")
    public void updateById(@PathVariable Integer id){
        userService.updateById(id);
    }


}
