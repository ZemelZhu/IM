package com.im.zemel.services;

import com.im.zemel.pojo.User;
import com.im.zemel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getMessage(Integer uid) {
        User user = userRepository.findOne(uid);
        if (user == null)
            return null;
        user.setPassword("");
        return user;
    }

    public void modifyUserMessage(User user) {
        User one = userRepository.findOne(user.getUid());
        one.setState(user.getState());
        one.setName(user.getName());
        one.setAvatar(user.getAvatar());
        userRepository.save(one);
    }

}
