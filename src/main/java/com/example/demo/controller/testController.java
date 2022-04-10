package com.example.demo.controller;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Name;
import com.example.demo.mapper.LogMapper;
import com.example.demo.service.ILogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Api(tags = "测试框架")
@RestController
public class testController {

    @Autowired
    ILogService logService;

//    @PostMapping
    @RequestMapping("/")
    public String index() {
        return "Hello springboot!";
    }

    @ApiOperation("获取名字列表")
    @GetMapping("/getName")
    public List<Name> getName() {
        System.out.println("getName");
        List<Name> result = logService.getName();
//        for (String s:
//                result) {
//            System.out.println(s);
//        }
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

    @RequestMapping("excelDownload")
    public void excelDownload(HttpServletResponse response) throws IOException {
        List<Name> nameList = this.getName();
        this.excelDownload(nameList,Name.class,response);
    }
    public <T>  void excelDownload(List<T> list, Class<T> clazz, HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String sDate = df.format(new Date());

        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("数据导出"+sDate, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
//		EasyExcel.write(response.getOutputStream(), clazz).sheet("sheet1").doWrite(list);
        EasyExcel.write("d:\\1123.xlsx", clazz).sheet("sheet1").doWrite(list);
    }

    /**
     * MongoDB存储json格式数据
     *
     * json数据可以是JSONObject，也可以是JSONArray
     *
     * @param jsonObject
     * @return
     */
    @Autowired
    private MongoTemplate mongoTemplate;
    @PostMapping("/mongoAddJsonData")
    public void mongoAddJsonData(@RequestBody JSONObject jsonObject){
        this.mongoTemplate.insert(jsonObject, "mongo_json_test");
    }

    @Autowired
    LogMapper logMapper;
    @PostMapping("/pageTest")
    public IPage<Name> pageTest(){
        Integer currentPage = 1; //当前页数：显示第一页数据
        Integer pageSize = 2;    //每页显示多少：每页显示2条数据
        Page<Name> page = new Page<Name>(currentPage, pageSize);
        IPage<Name> resultList = logMapper.getPageName(page);
       return resultList;
    }
}