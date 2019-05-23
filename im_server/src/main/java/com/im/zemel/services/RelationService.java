package com.im.zemel.services;

import com.im.zemel.component.GlobalComponent;
import com.im.zemel.component.UserComponent;
import com.im.zemel.pojo.Message;
import com.im.zemel.pojo.Relation;
import com.im.zemel.pojo.UserInfo;
import com.im.zemel.protoBean.CommonMsgPBOuterClass;
import com.im.zemel.repository.MessageRepository;
import com.im.zemel.repository.RelationRepository;
import com.im.zemel.repository.UserInfoRepository;
import com.im.zemel.repository.UserRepository;
import com.im.zemel.core.Iplayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationService {

    @Autowired
    private RelationRepository relationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private MessageRepository messageRepository;

//    public List<Message> getAllRelation(Integer uid) {
////        List<User> byUid = userRepository.findUserByUid(uid);
//        List<Message> byUid = messageRepository.getMessage(uid);
//        for (Object i : byUid)
//            System.out.println(i);
//        return byUid;
//    }

    public String addNewFriend(Integer sessionUser, Integer uid, boolean isAddMessage) {
        if (userRepository.findOne(uid) != null && sessionUser != uid) {
            Relation relation = new Relation();
            relation.setUid(sessionUser);
            relation.setFid(uid);
            List<Relation> relation1 = relationRepository.getRelation(sessionUser, uid);
            if (relation1 == null || relation1.size() == 0) {
                relationRepository.save(relation);
                UserInfo userInfo = userInfoRepository.findOne(sessionUser);
                userInfo.setFriendCount(userInfo.getFriendCount()+1);
                userInfoRepository.saveAndFlush(userInfo);
                userInfo = userInfoRepository.findOne(uid);
                userInfo.setFriendCount(userInfo.getFriendCount()+1);
                userInfoRepository.saveAndFlush(userInfo);
                if (isAddMessage) {
                    Message message = new Message(sessionUser, uid, "你好",0);
                    messageRepository.save(message);
                }

                UserComponent component = (UserComponent) GlobalComponent.getInstance().getComponent(UserComponent.NAME);
                Iplayer user = component.getUser(sessionUser);
                if (user != null) {
                    CommonMsgPBOuterClass.CommonMsgPB.Builder me = CommonMsgPBOuterClass.CommonMsgPB.newBuilder();
                    me.setType(CommonMsgPBOuterClass.MessageType.NewFriend);
                    user.send(me);
                }
                user = component.getUser(uid);
                if (user != null) {
                    CommonMsgPBOuterClass.CommonMsgPB.Builder me = CommonMsgPBOuterClass.CommonMsgPB.newBuilder();
                    me.setType(CommonMsgPBOuterClass.MessageType.NewFriend);
                    user.send(me);
                }
                return "success";
            }
        }
        return "false";
    }
}
