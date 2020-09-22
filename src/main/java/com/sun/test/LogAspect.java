package com.sun.test;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
        @Pointcut("@annotation(com.sun.test.LogAnno)")
        public void annotationCut(){}

        @Before("annotationCut()")
        public void before(){
            System.out.println("开始");
        }

        @After("annotationCut()")
        public void after(){
            System.out.println("结束");
        }
}
