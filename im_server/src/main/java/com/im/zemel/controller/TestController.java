package com.im.zemel.controller;

import com.im.zemel.util.RedisServiceUtil;
import com.im.zemel.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    public RedisServiceUtil redisServiceUtil;
    @RequestMapping("/checkNumber")
    public String hello()
    {
        redisServiceUtil.setKey("name","forezp");
        return redisServiceUtil.getValue("name");
    }
}
