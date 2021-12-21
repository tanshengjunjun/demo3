package com.example.demo.controller;
import com.example.demo.service.ILogService;
import com.example.demo.service.impl.LogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class testController {

    @Autowired
    ILogService logService;

//    @PostMapping
    @RequestMapping("/")
    public String index() {
        return "Hello springboot!";
    }

    @RequestMapping("/getName")
    public List<String> getName() {
        System.out.println("getName");
        List<String> result = logService.getName();
        for (String s:
                result) {
            System.out.println(s);
        }
        System.out.println(logService.getName().get(0));
        return logService.getName();
    }

    @RequestMapping("postName")
    public String postName(HttpServletRequest request){
        String a1 = request.getParameter("name1");
        System.out.println(a1);
        logService.insertNames();
        return "post Name 后裔" +
                "!";
    }
}