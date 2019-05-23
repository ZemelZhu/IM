package com.im.zemel.services;

import com.im.zemel.bean.MessageBean;
import com.im.zemel.pojo.Message;
import com.im.zemel.bean.MessageList;
import com.im.zemel.pojo.User;
import com.im.zemel.repository.MessageRepository;
import com.im.zemel.repository.RelationRepository;
import com.im.zemel.repository.UserRepository;
import com.im.zemel.type.MeeageStatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MessageService {
    @Autowired
    private RelationRepository relationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Transactional
    public List<MessageList> getAllMessageById(Integer uid) {
        List<Message> byUid = messageRepository.getMessage(uid);
        Map<Integer, List<MessageBean>> mp = new HashMap<Integer, List<MessageBean>>();
        if (byUid != null) {
            for (Message m : byUid) {
                int id = m.getFromId() != uid ? m.getFromId() : m.getToId();
                List<MessageBean> messages = mp.get(id);
                if (messages == null) {
                    messages = new ArrayList<MessageBean>();
                    mp.put(id, messages);
                }
                MessageBean mb = new MessageBean(m.getId(), m.getFromId(), m.getToId(), m.getStatus(), m.getContent(), m.getTime(),m.getType());
                messages.add(mb);
                if (messages.size() >= 4) {
                    MessageBean messageBean = messages.get(messages.size() - 4);
                    if (messageBean.getStatus() == MeeageStatusType.UNREAD.getValue()) {
                        messageBean.setStatus(MeeageStatusType.READ.getValue());
                        messageRepository.save(messageBean.getMessage());
                    }
                }
            }
        }
        List<User> userByUid = userRepository.findUserByUid(uid);
        List<MessageList> ml = new ArrayList<MessageList>();
        for (User u : userByUid) {
            MessageList m = new MessageList(u.getUid(), u.getName(), u.getAvatar(), mp.get(u.getUid()));
            ml.add(m);
        }
        Collections.sort(ml);
        return ml;
    }

    public List<MessageList> getMessageByFid(Integer uid, Integer fid) {
        List<Message> message = messageRepository.getMessage(uid, fid);
        List<MessageList> ml = new ArrayList<MessageList>();
        User u = userRepository.findOne(fid);
        List<MessageBean> messagesBean = new ArrayList<MessageBean>();
        for (Message m : message) {
            MessageBean mb = new MessageBean(m.getId(), m.getFromId(), m.getToId(), m.getStatus(), m.getContent(), m.getTime(),m.getType());
            messagesBean.add(mb);
        }
        MessageList m = new MessageList(u.getUid(), u.getName(), u.getAvatar(), messagesBean);
        ml.add(m);
        return ml;
    }
}
