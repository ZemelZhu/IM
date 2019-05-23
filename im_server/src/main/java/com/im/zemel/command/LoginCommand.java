package com.im.zemel.command;

import com.im.zemel.inf.ICommand;
import com.im.zemel.pojo.User;
import com.im.zemel.protoBean.CommonMsgPBOuterClass;
import com.im.zemel.services.LoginService;
import com.im.zemel.core.Iplayer;
import com.im.zemel.type.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = MessageType.LOGIN)
public class LoginCommand implements ICommand {
    @Autowired
    private LoginService loginService;

    @Override
    public void execute(Iplayer player, CommonMsgPBOuterClass.CommonMsgPB msgPB) {
        CommonMsgPBOuterClass.User userMessage = msgPB.getUser();
        if (userMessage != null) {
            User user = new User();
            user.setUid(userMessage.getUid());
            user.setPassword(userMessage.getPassword());
            if ("error".equals(loginService.userLogin(user, null))) {
                logger.error("login fail userID:" + user.getUid());
                player.offline();
                return;
            }
            player.login(user);
            logger.error("login success userID:" + userMessage.getUid());
        } else {
            logger.error("no find user");
        }
    }
}
