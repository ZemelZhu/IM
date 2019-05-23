package com.im.zemel.command;

import com.im.zemel.component.GlobalComponent;
import com.im.zemel.component.UserComponent;
import com.im.zemel.inf.ICommand;
import com.im.zemel.pojo.Message;
import com.im.zemel.protoBean.CommonMsgPBOuterClass;
import com.im.zemel.repository.MessageRepository;
import com.im.zemel.core.Iplayer;
import com.im.zemel.type.MeeageStatusType;
import com.im.zemel.type.MessageType;
import com.im.zemel.util.TimeUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value = MessageType.MSG)
public class MessageCommand implements ICommand {
    @Autowired
    private MessageRepository messageRepository;
    @Value("${administrator}")
    private Integer administrator;

    @Override
    public void execute(Iplayer player, CommonMsgPBOuterClass.CommonMsgPB msgPB) {
        int id = msgPB.getToId();
        UserComponent component = (UserComponent) GlobalComponent.getInstance().getComponent(UserComponent.NAME);
        Iplayer user = component.getUser(id);

        Message message = new Message(player.getId(), msgPB.getToId(), msgPB.getContent(), msgPB.getContentType());
        if (id == administrator || player.getId() == administrator)
            message.setStatus(MeeageStatusType.UNCHANGEABLE.getValue());
        messageRepository.save(message);
        logger.error(message + "###");
        CommonMsgPBOuterClass.CommonMsgPB.Builder me = CommonMsgPBOuterClass.CommonMsgPB.newBuilder();
        me.setType(CommonMsgPBOuterClass.MessageType.Msg);
        me.setFromId(player.getId());
        me.setToId(id);
        me.setContent(msgPB.getContent());
        me.setTime(TimeUntil.getNowDataString());
        me.setContentType(msgPB.getContentType());
        if (user != null)
            user.send(me);
        logger.error(user + "");
        player.send(me);
    }
}
