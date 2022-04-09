package com.example.demo.service.impl;

import com.example.demo.entity.Name;
import com.example.demo.mapper.LogMapper;
import com.example.demo.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements ILogService {

    @Autowired
    LogMapper logMapper;

    public List<Name> getName(){
        System.out.println("1");
        return logMapper.getName();
    }
    public void insertNames(){
        logMapper.insertNames();
    }
}
