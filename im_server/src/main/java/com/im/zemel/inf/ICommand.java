package com.im.zemel.inf;

import com.im.zemel.protoBean.CommonMsgPBOuterClass;
import com.im.zemel.core.Iplayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ICommand {
    static final Logger logger = LoggerFactory.getLogger(ICommand.class);

    void execute(Iplayer player, CommonMsgPBOuterClass.CommonMsgPB msgPB);
}
