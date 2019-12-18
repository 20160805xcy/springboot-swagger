# springboot-swagger
springboot-swagger(增删改查接口示例show),数据库查询使用通用Mapper

* 访问路径: http://192.168.6.102:8783/swagger-ui.html


### iReport-5.6.0

导入iTextAsian.jar 将此包手动添加到classpath
解决错误
>net.sf.jasperreports.engine.JRRuntimeException: Could not load the following font : 
 pdfFontName   : STSong-Light
 pdfEncoding   : UniGB-UCS2-H
 isPdfEmbedded : true

### AOP方式-优雅的打印日志信息
通过自定义注解的方式，在 Spring Boot 中来实现 AOP 切面统一打印出入参日志
先看效果,代码预览

![aop](https://raw.githubusercontent.com/20160805xcy/staticSource/master/images/Snipaste_2019-12-18_12-10-54.png )
![aop](https://raw.githubusercontent.com/20160805xcy/staticSource/master/images/Snipaste_2019-12-18_12-11-53.png )

只想在开发环境和测试环境中使用？加上注解 @Profile({"dev","test"})

    @Aspect
    @Component
    @Profile({"dev","test"})
    public class WebLogAspect {
    
        private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
        //换行符
        private static final String LINE_SEPARATOR = System.lineSeparator();

