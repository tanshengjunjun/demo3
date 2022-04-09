package com.example.demo.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
@ExcelIgnoreUnannotated
public class Name {

    @ExcelProperty(value = "ID",index = 1)
    private int id;
    @ExcelProperty(value = "名字",index = 2)
    private String name;
    @ExcelProperty(value = "年龄",index = 3)
    private int age;
}
