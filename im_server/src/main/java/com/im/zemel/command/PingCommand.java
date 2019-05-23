package com.im.zemel.command;

import com.im.zemel.inf.ICommand;
import com.im.zemel.protoBean.CommonMsgPBOuterClass;
import com.im.zemel.core.Iplayer;
import com.im.zemel.type.MessageType;
import org.springframework.stereotype.Component;

@Component(value = MessageType.PING)
public class PingCommand implements ICommand {
    @Override
    public void execute(Iplayer player, CommonMsgPBOuterClass.CommonMsgPB msgPB) {
        player.resetPing();
    }
}
