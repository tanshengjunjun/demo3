package com.example.demo.service;

import com.example.demo.entity.Name;

import java.util.List;

public interface ILogService {
    List<Name> getName();
    void insertNames();
}
