package com.im.zemel.services;

import com.im.zemel.pojo.User;
import com.im.zemel.pojo.UserInfo;
import com.im.zemel.repository.MessageRepository;
import com.im.zemel.repository.RelationRepository;
import com.im.zemel.repository.UserInfoRepository;
import com.im.zemel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FriendService {
    @Autowired
    private RelationRepository relationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private MessageRepository messageRepository;
    public List<User> getAllUser(Integer uid) {
        List<User> userByUid = userRepository.findUserByUid(uid);
        for (User user : userByUid)
            user.setPassword("");
        return userByUid;
    }
    @Transactional
    public String deleteFriend(Integer uid, Integer fid) {
        relationRepository.deleteRelation(uid,fid);
        messageRepository.deleteMessage(uid,fid);
        UserInfo userInfo = userInfoRepository.findOne(uid);
        userInfo.setFriendCount(userInfo.getFriendCount()-1);
        userInfoRepository.saveAndFlush(userInfo);
        return "success";
    }

    public List<User> getAllUserByPage(Integer uid, int page) {
        List<User> userByUid = userRepository.findUserByUidAndPage(uid,page *9);
        for (User user : userByUid)
            user.setPassword("");
        return userByUid;
    }
}
