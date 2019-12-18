package com.xcy.springbootswagger.config.aop;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author
 * @date 2019/12/18 10:43
 * @description
 * @since V1.0.0
 */
@Aspect
@Component
@Profile({"dev","test"})
public class WebLogAspect {

    private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
    //换行符
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * 以自定义 @WebLog 注解为切点
     */
    @Pointcut("@annotation(com.xcy.springbootswagger.config.aop.WebLog)")
    public void webLog() {
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        logger.info("Response Args    : {}", new Gson().toJson(result));
        //执行耗时
        logger.info("Time-Consuming   : {}", System.currentTimeMillis() - startTime);
        return result;
    }


    @Before("webLog()")
    public void doBefore(JoinPoint joinpoint) throws Throwable {
        //开始打印请求日志
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        //获取@WebLog注解描述信息
        String methodDescription = getAspectLogDescription(joinpoint);

        //打印相关的请求参数
        logger.info("===============================Start=================================");
        //打印请求URL
        logger.info("URL              : {}", request.getRequestURL().toString());
        //打印描述信息
        logger.info("Description      : {}", methodDescription);
        //打印HTTP method
        logger.info("HTTP Method      : {}", request.getMethod());
        //打印Controller的全路径和执行方法
        logger.info("Class Method     : {}.{}", joinpoint.getSignature().getDeclaringType(), joinpoint.getSignature().getName());
        //打印请求的ip
        logger.info("IP               : {}", request.getRemoteAddr());
        //打印请求的参数
        logger.info("Requests Args    : {}", JSONObject.toJSONString(joinpoint.getArgs()));

    }

    @After("webLog()")
    public void doAfter() {
        //接口结束后换行,方便分割查看
        logger.info("=============================== End =================================" + LINE_SEPARATOR);
    }

    private String getAspectLogDescription(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        Object object = joinPoint.getTarget();

        //MethodSignature是Signature的子类
        MethodSignature methodSignature = (MethodSignature) signature;

        Method targetMethod = methodSignature.getMethod();

        StringBuilder stringBuilder = new StringBuilder();

        boolean methodPresent = targetMethod.isAnnotationPresent(WebLog.class);

        if (methodPresent) {
            //得到requestMapping注释
            WebLog methodAnnotation = targetMethod.getAnnotation(WebLog.class);
            String methodAnnotationValues = methodAnnotation.description();
            stringBuilder.append(methodAnnotationValues);
        }
        return stringBuilder.toString();
    }


}
