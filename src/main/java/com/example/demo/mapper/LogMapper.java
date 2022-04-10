package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Name;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogMapper {

    List<Name> getName();

    void insertNames();

    IPage<Name> getPageName(Page<Name> page);
}
