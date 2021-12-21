package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//@Slf4j
//@SpringBootTest
class Demo3ApplicationTests {

    @BeforeEach
    public void s(){
        System.out.println("before?");
    }
    @Test
    void contextLoads() {
        System.out.println("123test");

    }

}
