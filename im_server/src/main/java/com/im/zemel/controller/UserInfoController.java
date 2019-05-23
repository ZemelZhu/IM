package com.im.zemel.controller;

import com.im.zemel.pojo.UserInfo;
import com.im.zemel.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/UserInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService manageService;
    @RequestMapping("/userInfo")
    public UserInfo getAllManageSize(HttpSession session) {
        return manageService.getUserInfo(session);
    }
}
