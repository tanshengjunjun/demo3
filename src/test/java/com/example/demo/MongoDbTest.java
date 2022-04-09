package com.example.demo;

import com.example.demo.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDbTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testMongoDbTemplate(){
        List<Student> stuList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        stringList.add("仙剑1");
        stringList.add("仙剑3");
        stringList.add("仙剑7");
        stuList.add(new Student(1,"胡歌","男",stringList));
        stuList.add(new Student(2,"刘亦菲","女",stringList));
        stuList.add(new Student(3,"易烊千玺","男",stringList));
        stuList.add(new Student(4,"沈腾","男",stringList));
        stuList.add(new Student(5,"陈钰琪","女",stringList));
        mongoTemplate.save(stuList, "stuList");
    }
}
