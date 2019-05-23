package com.im.zemel.task;

import com.google.protobuf.ByteString;
import com.im.zemel.core.Iplayer;
import com.im.zemel.inf.ICommand;
import com.im.zemel.protoBean.CommonMsgPBOuterClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task implements Runnable {
    private CommonMsgPBOuterClass.CommonMsgPB msgPB;
    protected static final Logger logger = LoggerFactory.getLogger(Task.class);
    /**
     * 指令
     */
    protected ICommand cmd;

    /**
     * 通用消息
     */
    protected ByteString message;
    protected Iplayer player;

    public Task(Iplayer player, CommonMsgPBOuterClass.CommonMsgPB msgPB, ICommand cmd) {
        this.player = player;
        this.cmd = cmd;
        this.msgPB = msgPB;
    }

    public void run() {
        try {
            this.cmd.execute(this.player, this.msgPB);
        } catch (Throwable e) {
            logger.error("cmd run error" + e);
        } finally {
            player.finishOneCommandTask();
        }
    }
}
