package com.example.demo;

import com.example.demo.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
//import org.junit.platform.commons.logging.Logger;
//import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {
//    Logger log = LoggerFactory.getLogger(RedisTests.class);

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    public RedisUtils redisUtils;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() throws Exception {

        // 保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));

    }

    @Test
    public void get1(){
        long start = System.currentTimeMillis();
        redisUtils.get("a");
        System.out.println(redisUtils.get("a"));
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    @Test
    public void set(){
        redisTemplate.opsForValue().set("a","易大师1",30, TimeUnit.MINUTES);
        log.info("设置 key 为 a 的值是：{}",1);
        redisTemplate.opsForValue().set("b","易大师2",30, TimeUnit.MINUTES);
        log.info("设置 key 为 a 的值是：{}",1);
        redisTemplate.opsForValue().set("c","易大师3",30, TimeUnit.MINUTES);
        log.info("设置 key 为 a 的值是：{}",1);
    }
    @Test
    public void get(){
        String value =  redisTemplate.opsForValue().get("a");
        log.info("key 为 a 的值是：{}",value);
    }
    @Test
    public void del(){
        redisTemplate.delete("a");
        log.info("删除key 为 a 的值是：{}",1);
    }

    // list数据类型适合于消息队列的场景:比如12306并发量太高，而同一时间段内只能处理指定数量的数据！必须满足先进先出的原则，其余数据处于等待
    @Test
    public void listPushResitTest() {
        // leftPush依次由右边添加
        stringRedisTemplate.opsForList().rightPush("myList", "1");
        stringRedisTemplate.opsForList().rightPush("myList", "2");
        stringRedisTemplate.opsForList().rightPush("myList", "A");
        stringRedisTemplate.opsForList().rightPush("myList", "B");
        // leftPush依次由左边添加
        stringRedisTemplate.opsForList().leftPush("myList", "0");
    }

    @Test
    public void listGetListResitTest() {
        // 查询类别所有元素
        List<String> listAll = stringRedisTemplate.opsForList().range("myList", 0, -1);
        log.info("list all {}", listAll);
        // 查询前3个元素
        List<String> list = stringRedisTemplate.opsForList().range("myList", 0, 3);
        log.info("list limit {}", list);
    }

    @Test
    public void listRemoveOneResitTest() {
        // 删除先进入的B元素
        stringRedisTemplate.opsForList().remove("myList", 1, "B");
    }

    @Test
    public void listRemoveAllResitTest() {
        // 删除所有A元素
        stringRedisTemplate.opsForList().remove("myList", 0, "A");
    }

    @Test
    public void testRedis(){
        int i = 0;
        try {
            long startTime = System.currentTimeMillis();
            while(true){
                long endTime = System.currentTimeMillis();
                if(endTime - startTime >= 3000){
                    break;
                }
                redisTemplate.opsForValue().set("test"+i,i+"");
                i++;
            }
        }finally {
            log.info("redis 每秒操作"+i+"次");
            System.out.println("redis 每秒操作"+i+"次");
        }
    }

}
