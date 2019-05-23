package com.im.zemel.net;

import com.im.zemel.inf.ICommand;
import com.im.zemel.protoBean.CommonMsgPBOuterClass;
import com.im.zemel.core.Iplayer;
import com.im.zemel.task.Task;
import com.im.zemel.util.SpringUtil;
import io.netty.channel.Channel;


public class NettyClientConnection extends AbstractClientConnection {

    public NettyClientConnection(Channel channel, int readByteslen) {
        super(channel, readByteslen);
    }

    public byte[] getReadBytes() {

        return readBytes;
    }

    public void process(NettyClientConnection conn, CommonMsgPBOuterClass.CommonMsgPB msgPB) {
        // 建立任务加入线程池
        Iplayer holder = conn.getHolder();
//        CommandComponent cm =(CommandComponent) GlobalComponent.getInstance().getComponent(CommandComponent.NAME);
//        String name = msgPB.getName();
//        ICommand cmd = cm.getCommand((short) 1);
        if (holder == null) {
            holder = new Iplayer();
            conn.setHolder(holder);
        }
        ICommand acm = SpringUtil.getCommand(String.valueOf(msgPB.getType().getNumber()));
//        logger.error("cmd Type:"+msgPB.getType());
        if(acm!=null)
        {
            Task task = new Task(holder, msgPB, acm);
            holder.addCommandTask(task);
        }
        else
        {
            logger.error("no find command");
        }

    }

}
