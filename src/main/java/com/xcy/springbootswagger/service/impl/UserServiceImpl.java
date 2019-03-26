package com.xcy.springbootswagger.service.impl;

import com.xcy.springbootswagger.mapper.UserMapper;
import com.xcy.springbootswagger.model.User;
import com.xcy.springbootswagger.service.UserService;
import com.xcy.springbootswagger.util.PDFUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Override
    public String exportPDF(Integer id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = new User();
        user.setId(id);
        //1.JAVABean 方式打印一条数据(实体的名称和模板内的(通过sql查询出来的field不一致的情况下)) 封装成json对象list
        //User returnUser = userMapper.selectOne(user);
        //String result = PDFUtil.exportPDFByJRBeanCollectionDataSource1(request, response, returnUser);

        //2.JAVABean 方式打印列表数据(实体字段和iReport的field字段一致)
        List<User> userList = userMapper.selectAll();
        String result = PDFUtil.exportPDFByJRBeanCollectionDataSource3(request, response, userList);

        //3.JAVABean 方式打印列表数据(实体字段和iReport的field字段一致)
        //String result = PDFUtil.exportPDFByJRBeanCollectionDataSource2(request, response);

        //4.Connection 方式打印(不推荐)
        //String result = PDFUtil.exportPDFByConnection();

        return result;
    }
}
