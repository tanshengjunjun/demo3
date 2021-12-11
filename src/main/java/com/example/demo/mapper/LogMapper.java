package com.example.demo.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface LogMapper {

    List<String> getName();

    void insertNames();
}
