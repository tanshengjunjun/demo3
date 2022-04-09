package com.example.demo.mapper;

import com.example.demo.entity.Name;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface LogMapper {

    List<Name> getName();

    void insertNames();
}
