package com.im.zemel.controller;

import com.im.zemel.pojo.User;
import com.im.zemel.services.UserService;
import com.im.zemel.util.SessionUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/Message")
    public User getMessage(@RequestBody Map map) {
        Integer uid = (Integer) map.get("uid");
        if (uid != null) {
            return userService.getMessage(uid);
        }
        return null;
    }

    @PostMapping("/modifyUserMessage")
    public void modifyUserMessage(@RequestBody User user, HttpSession session) {
        Integer uid = SessionUntil.getSessionUser(session);
        if (uid != null && user.getUid() == (int) uid) {
            userService.modifyUserMessage(user);
        }
    }
    @RequestMapping("/getUserMessageById")
    public User getUserMessageById(@RequestBody Map map) {
        //获得用户信息
        Integer userId= Integer.parseInt(map.get("userId").toString());
        return userService.getMessage(userId);
    }
}
