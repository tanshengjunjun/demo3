package com.example.demo.controller;

import com.example.demo.dao.Book;
import com.example.demo.service.MongoDbService;
//import com.example.mqdemo.mongo.Book;
//import com.example.mqdemo.mongo.MongoDbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author: huangyibo
 * @Date: 2019/1/30 23:31
 * @Description:
 */
@RestController
public class MongoController {

    @Autowired
    private MongoDbService mongoDbService;

    @PostMapping("/mongo/save")
    public String saveObj(@RequestBody Book book) {return mongoDbService.saveObj(book);}

    @GetMapping("/mongo/findAll")
    public List<Book> findAll() {return mongoDbService.findAll();}

    @GetMapping("/mongo/findOne")
    public Book findOne(@RequestParam String id) {return mongoDbService.getBookById(id);}

    @GetMapping("/mongo/findOneByName")
    public Book findOneByName(@RequestParam String name) {return mongoDbService.getBookByName(name);}

    @PostMapping("/mongo/update")
    public String update(@RequestBody Book book) {return mongoDbService.updateBook(book);}

    @PostMapping("/mongo/delOne")
    public String delOne(@RequestBody Book book) {return mongoDbService.deleteBook(book);}

    @GetMapping("/mongo/delById")
    public String delById(@RequestParam String id) {return mongoDbService.deleteBookById(id);}
}