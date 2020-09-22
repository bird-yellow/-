package com.sun.Common;

import com.sun.Entity.Log;
import com.sun.Service.LogService;
import com.sun.Utils.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class LogAspect {

        @Pointcut("@annotation(com.sun.Common.LogAnno)")
        public void  annotationCut(){}

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogService sysLogService;

    private Date startTime; // 开始时间
    private Class clazz; // 访问的类
    private Method method; // 访问的方法

        @Before("annotationCut()")
        public void before(JoinPoint jp) throws Exception {
            System.out.println("开始");
            startTime = new Date(); // 访问时间
            // 获取访问的类
            clazz = jp.getTarget().getClass();
            // 获取访问的方法名
            String methodName = jp.getSignature().getName();
            // 获取访问的方法的参数
            Object[] args = jp.getArgs();
            if(args == null || args.length == 0){
                // 获取无参数的方法
                method = clazz.getMethod(methodName);
            }else {
                // 有参数，则将args中的所有元素遍历，获取对应的class,装入Class[]数组中
                Class[] classes = new Class[args.length];
                for(int i = 0; i < args.length; i++){
                    classes[i] = args[i].getClass();
                }
                // 获取有参方法
                method = clazz.getMethod(methodName, classes);
            }
        }

        @After("annotationCut()")
        public void after(JoinPoint jp) throws Exception {
            System.out.println("结束");
            Signature signature = jp.getSignature();
            Method method = ((MethodSignature)signature).getMethod();

            Method realMethod = jp.getTarget().getClass().getDeclaredMethod(signature.getName(),method.getParameterTypes());
            LogAnno logAnno = realMethod.getAnnotation(LogAnno.class);
            System.out.println("请求的IP地址" + IpUtils.getIpAddress());
            Log log = new Log();
            log.setType(logAnno.value());
            log.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            log.setName("张三");
            sysLogService.insert(log);
            System.out.println("数据插入成功");
        }
}
