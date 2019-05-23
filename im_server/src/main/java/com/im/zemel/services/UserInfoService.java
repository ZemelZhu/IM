package com.im.zemel.services;

import com.im.zemel.pojo.UserInfo;
import com.im.zemel.repository.UserInfoRepository;
import com.im.zemel.util.SessionUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    public UserInfo getUserInfo(HttpSession session) {
        Integer userId = SessionUntil.getSessionUser(session);
        if (userId != null) {
            return userInfoRepository.findOne(userId);
        }
        return null;
    }
}
