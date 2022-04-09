package com.example.demo.entity;

import lombok.Data;

import java.util.List;

@Data
public class Student {
    private int id;
    private String name;
    private String sex;
    private List<String> list;
    public Student(int id,String name,String sex,List<String> list){
        this.id = id;
        this.name = name;
        this.sex =  sex;
        this.list = list;
    }
}
