package com.sun.test;

import org.springframework.stereotype.Component;

@Component
public class LogServiceImpl implements  LogService {
    @LogAnno(value = "abc")
    @Override
    public void insert() {
        System.out.println("insert one data");
    }

    @LogAnno(value = "def")
    @Override
    public void delete() {
        System.out.println("delete one data");
    }
}
