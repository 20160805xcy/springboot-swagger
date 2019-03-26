package com.xcy.springbootswagger.util;

import com.alibaba.fastjson.JSONObject;
import com.xcy.springbootswagger.model.TableVo;
import com.xcy.springbootswagger.model.User;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xcy
 * @version V1.0.0
 * @description PDF工具类
 * @date 2019/03/21 16:31
 */
public class PDFUtil {

    /**
     * 将查询出来的java实体对象封装成list集合,list集合存的是一个个字段的对应的json对象.
     * 打印一条数据
     * 这种情况适用于 实体的名称和模板内的(通过sql查询出来的field不一致的情况下),可以带条件,使用.
     * @param request
     * @param response
     * @param user     用户信息
     * @return
     * @throws JRException
     * @throws IOException
     */
    public static String exportPDFByJRBeanCollectionDataSource1(HttpServletRequest request, HttpServletResponse response, User user) throws JRException, IOException {

        File path;

        // 获取工程项目资源路径
        path = new File(ResourceUtils.getURL("classpath:").getPath());

        // 获取jasper模板
        File file = new File(path + "/jasper/User0322Test.jasper");

        //获取jasper模板
        JasperReport template = (JasperReport) JRLoader.loadObject(file);

        //生成pdf文件路径
        File newFile = new File(path + "/jasper/User0322Test.pdf");

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("id", 1);

        //组装数据
        List listData = new ArrayList();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", user.getId());
        jsonObject.put("user_name", user.getUserName());
        jsonObject.put("user_age", user.getUserAge());
        jsonObject.put("user_address", user.getUserAddress());

        listData.add(jsonObject);

        try {
            ByteArrayOutputStream outPut = new ByteArrayOutputStream();
            FileOutputStream outputStream;
            JasperPrint jasperPrint = JasperFillManager.fillReport(template, parameters, new JRBeanCollectionDataSource(listData, false));

            JRAbstractExporter exporter = new JRPdfExporter();
            // 创建jasperPrint
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            // 生成输出流
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outPut);
            exporter.exportReport();

            // 在target目录下生成pdf文件
            outputStream = new FileOutputStream(newFile);
            outputStream.write(outPut.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "success";
    }


    /**
     * 将自己创建出来的java实体对象封装成list集合,list集合存的是多个java实体对象.
     * 实体对象要求各个字段跟irReport定义的字段一致.
     * @param request
     * @param response
     * @return
     * @throws JRException
     * @throws IOException
     */
    public static String exportPDFByJRBeanCollectionDataSource2(HttpServletRequest request, HttpServletResponse response) throws JRException, IOException {

        File path;
        // 获取工程项目资源路径
        path = new File(ResourceUtils.getURL("classpath:").getPath());

        // 获取jasper模板
        File file = new File(path + "/jasper/table_test.jasper");

        List<TableVo> reports = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TableVo tableVo = new TableVo();
            tableVo.setId(i + "");
            tableVo.setName("名称" + i);
            tableVo.setNumber(i);
            tableVo.setLable("第" + i + "类");
            tableVo.setShowFlag(true);
            reports.add(tableVo);
        }

        //生成pdf文件路径
        File newFile = new File(path + "/jasper/tableTest.pdf");

        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(reports);

        Map parameters = new HashMap();
        parameters.put("queryKey", "a query field");

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.setContentType("application/pdf");

        JRAbstractExporter exporter = new JRPdfExporter();

        JasperPrint jasperPrint = JasperFillManager.fillReport(file.getPath(), parameters, beanColDataSource);

        //方式1:直接浏览器下载pdf文件
        //request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
        //OutputStream out = response.getOutputStream();
        //exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        //exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
        //exporter.exportReport();
        //out.flush();


        //方式2:写文件方式写到指定路径下
        ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        FileOutputStream outputStream;
        // 生成输出流
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outPut);
        exporter.exportReport();

        // 在target目录下生成pdf文件
        outputStream = new FileOutputStream(newFile);
        outputStream.write(outPut.toByteArray());

        return "success";
    }


    /**
     * 将查询出来的java实体对象封装成list集合,list集合存的是User实体对象
     * 打印列表数据
     * 先通过ireport创建field,名称要求跟实体一样.
     * @param request
     * @param response
     * @param userList 用户信息list
     * @return
     * @throws JRException
     * @throws IOException
     */
    public static String exportPDFByJRBeanCollectionDataSource3(HttpServletRequest request, HttpServletResponse response, List<User> userList) throws JRException, IOException {
        File path;
        //获取工程项目资源路径
        path = new File(ResourceUtils.getURL("classpath:").getPath());

        //获取jasper模板文件
        File file = new File(path + "/jasper/userListByBo.jasper");

        //获取jasper模板
        JasperReport template = (JasperReport) JRLoader.loadObject(file);

        //生成pdf文件路径
        File newFile = new File(path + "/jasper/userListByBo.pdf");

        //额外参数(可用作查询条件,也可用作固定参数)
        HashMap<String, Object> parameters = new HashMap<>();

        //将userList转成javaBean数据源
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(userList);

        try {
            ByteArrayOutputStream outPut = new ByteArrayOutputStream();
            FileOutputStream outputStream;
            JasperPrint jasperPrint = JasperFillManager.fillReport(template, parameters, jrBeanCollectionDataSource);

            JRAbstractExporter exporter = new JRPdfExporter();
            // 创建jasperPrint
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            // 生成输出流
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outPut);
            //exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(""));
            exporter.exportReport();

            // 在target目录下生成pdf文件
            outputStream = new FileOutputStream(newFile);
            outputStream.write(outPut.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

}
