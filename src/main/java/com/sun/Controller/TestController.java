package com.sun.Controller;

import com.sun.Common.LogAnno;
import com.sun.Entity.Log;
import com.sun.Service.Impl.LogServiceImpl;
import com.sun.Service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/log")
public class TestController {



    @LogAnno(value = "测试")
    @RequestMapping("/test")
    public String test(){
        System.out.println("进入到测试");
        return "log";
    }
}
