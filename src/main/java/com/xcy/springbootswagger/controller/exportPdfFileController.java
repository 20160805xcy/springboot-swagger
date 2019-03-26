package com.xcy.springbootswagger.controller;

import com.xcy.springbootswagger.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xcy
 * @version V1.0.0
 * @description 导出PDF文件
 * @date 2019/03/21 16:27
 */
@Api(description = "导出PDF文件")
@RestController("export")
public class exportPdfFileController {

    @Autowired
    UserService userService;

    @ApiOperation("使用JasperReport+iReport导出PDF文件")
    @PostMapping("exportPDF")
    public String exportPDF(Integer id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result = userService.exportPDF(id, request, response);
        return result;
    }
}
