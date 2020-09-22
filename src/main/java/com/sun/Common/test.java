package com.sun.Common;

import com.sun.Entity.Log;
import com.sun.Service.LogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-aop.xml")
public class test {
    @Autowired
    private LogService logService;

    @Test
    public void test(){
        Log log = new Log();
        log.setTime(new Date().toString());
        log.setName("张三");
        log.setType("你好");
            logService.insert(log);
    }
}
