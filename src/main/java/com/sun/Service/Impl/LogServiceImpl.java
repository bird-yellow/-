package com.sun.Service.Impl;

import com.sun.Common.LogAnno;
import com.sun.Dao.LogDao;
import com.sun.Entity.Log;
import com.sun.Service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public void insert(Log log) {
            logDao.insert(log);
    }
}
