package com.im.zemel.controller;

import com.im.zemel.pojo.User;
import com.im.zemel.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/Login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/checkNumber")
    public String checkNumber(@RequestBody Map map) {
        int userId = Integer.parseInt(map.get("number").toString());
        return loginService.checkNumber(userId);
    }

    @PostMapping("/generateUserId")
    public String generateUserId() {
        return loginService.generateUserId();
    }

    @PostMapping("/getVerificationCode")
    public String getVerificationCode(@RequestBody Map map, HttpSession session) {
        String emailTo = map.get("emailTo").toString();
        return loginService.getVerificationCode(emailTo, session);
    }

    @RequestMapping("/userLogin")
    public String userLogin(@RequestBody User user, HttpSession session) {
        return loginService.userLogin(user, session);
    }

    @PostMapping("/intercepter/getLogin")
    public User getLogin(HttpSession session) {
        return loginService.getLogin(session);
    }

    @RequestMapping("/checkEmail")
    public String checkEmail(@RequestBody Map map) {
        String email = map.get("email").toString();
        return loginService.checkEmail(email);
    }

    @RequestMapping("/register")
    public String register(@RequestBody Map map, HttpSession session) {
        int userId = (int) map.get("number");
        String password = (String) map.get("pass");
        String email = (String) map.get("email");
        String verificationCode = (String) map.get("verificationCode");
        System.out.println(userId + "==>" + password + "==>" + email + "==>" + verificationCode);
        return loginService.register(userId, password, email, verificationCode, session);
    }

    @PostMapping("/offLine")
    public String offLine(HttpSession session) {
        return loginService.offLine(session);
    }
}
